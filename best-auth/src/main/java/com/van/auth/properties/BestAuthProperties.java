package com.van.auth.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author van.shu
 * @create 2020/8/6 21:17
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:best-auth.properties"}) //用于指定读取配置文件的位置
@ConfigurationProperties(prefix = "best.auth") //要读取的属性的统一前缀名
public class BestAuthProperties {


    private BestClientsProperties[] clients = {};

    /**
     * access_token的有效时间
     */
    private int accessTokenValiditySeconds = 60 * 60 * 24;

    /**
     * refresh_token的有效时间
     */
    private int refreshTokenValiditySeconds60 = 60 * 24 * 7;
}
