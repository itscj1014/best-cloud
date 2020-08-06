package com.van.auth.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author van.shu
 * @create 2020/8/6 21:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BestClientsProperties {
    /**
     * client_id
     */
    private String client;
    /**
     * secret
     */
    private String secret;
    /**
     * 当前令牌支持的认证类型
     */
    private String grantType = "password,authorization_code,refresh_token";
    /**
     * 认证范围
     */
    private String scope = "all";
}
