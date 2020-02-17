package com.fosung.demo.config;

import com.fosung.demo.constant.SsoConstant;
import com.fosung.demo.service.SysClientDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SysClientDetailService sysClientDetailService;


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        String finalSecret = "{bcrypt}"+new BCryptPasswordEncoder().encode("123456");

        /*clients.inMemory()
                .withClient("client_1")
                .resourceIds(SsoConstant.DEMO_RESOURCE_ID)
                .authorizedGrantTypes("client_credentials","refresh_token","authorization_code")
                .redirectUris("http://127.0.0.1:8081/user/callback")
                .scopes("all")
                .authorities("oauth2")
                .secret(finalSecret)
                .and()
                .withClient("client_2")
                .resourceIds(SsoConstant.DEMO_RESOURCE_ID)
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("select")
                .authorities("oauth2")
                .secret(finalSecret);*/

        clients.withClientDetails(sysClientDetailService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

       endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory))
               .allowedTokenEndpointRequestMethods(HttpMethod.POST,HttpMethod.GET)
               .authenticationManager(authenticationManager);

       //自定义授权页面
       endpoints.pathMapping("/oauth/confirm_access","/auth/customConfirm");
    }
}
