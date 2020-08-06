package com.van.auth.configure;

import com.van.auth.properties.BestAuthProperties;
import com.van.auth.properties.BestClientsProperties;
import com.van.auth.service.BestUserDetailService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 认证服务器安全配置类
 *
 * @author van.shu
 * @create 2020/8/6 15:08
 */
@Configuration
@EnableAuthorizationServer
public class BestAuthorizationServerConfigure extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;


    @Autowired
    private BestUserDetailService bestUserDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BestAuthProperties bestAuthProperties;




    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        BestClientsProperties[] clientsArray = bestAuthProperties.getClients();

        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();

        if (!ArrayUtils.isEmpty(clientsArray)) {
            for (BestClientsProperties client : clientsArray) {

                if (StringUtils.isBlank(client.getClient())) {
                    throw new Exception("client 不能为空");
                }

                if (StringUtils.isBlank(client.getSecret())) {
                    throw new Exception("secret 不能为空");
                }

                String[] grantTypes = StringUtils.splitByWholeSeparatorPreserveAllTokens(client.getGrantType(), ",");
                builder.withClient(client.getClient())
                        .secret(passwordEncoder.encode(client.getSecret()))
                        .authorizedGrantTypes(grantTypes)
                        .scopes(client.getScope());

            }
        }


//        clients.inMemory()
//                //如果需要指定多个client，可以继续使用withClient
//                .withClient("best-cloud")
//                .secret(passwordEncoder.encode("123456"))
//                .authorizedGrantTypes("password", "refresh_token")
//                .scopes("all");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(bestUserDetailService)
                .tokenServices(defaultTokenServices());

    }

    /**
     * 认证服务器存储的令牌在redis
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Bean
    @Primary
    public DefaultTokenServices defaultTokenServices() {

        DefaultTokenServices tokenServices = new DefaultTokenServices();

        tokenServices.setTokenStore(tokenStore());

        tokenServices.setSupportRefreshToken(true);

        tokenServices.setAccessTokenValiditySeconds(bestAuthProperties.getAccessTokenValiditySeconds());

        tokenServices.setRefreshTokenValiditySeconds(bestAuthProperties.getRefreshTokenValiditySeconds60());

        return tokenServices;
    }
}
