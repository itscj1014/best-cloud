package com.van.auth.properties;

import lombok.Data;

/**
 * @author van.shu
 * @create 2020/8/6 21:12
 */
@Data
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
