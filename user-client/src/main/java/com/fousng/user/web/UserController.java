package com.fousng.user.web;

import com.alibaba.fastjson.JSON;
import com.fousng.user.util.HttpClientUtil;

import com.fousng.user.util.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@Controller
public class UserController {

    @Value("${sso.token_url}")
    private String tokenUrl;

    @Value("${sso.client_id}")
    private String clientId;

    @Value("${sso.client_secret}")
    private String clientSecret;

    @Value("${sso.scope}")
    private String scope;

    @Value("${sso.grant_type}")
    private String grantType;

    @Value("${sso.redirect_uri}")
    private String redirectUri;

    @RequestMapping("/callback")
    @ResponseBody
    public String callback(@RequestParam("code") String code) {
        System.out.println("授权码：" + code);

        Map<String, String> param = new HashMap<>();

        param.put("client_id", clientId);
        param.put("client_secret", clientSecret);
        param.put("grant_type", grantType);
        param.put("code", code);
        param.put("redirect_uri", redirectUri);

        Object result = HttpClientUtil.doPost(tokenUrl, param);
        return JSON.toJSONString(result);
    }

}
