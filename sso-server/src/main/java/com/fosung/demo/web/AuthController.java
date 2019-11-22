package com.fosung.demo.web;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 自定义授权页面
 */
@Controller
@SessionAttributes("authorizationRequest")
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping("/customConfirm")
    public ModelAndView customConfirm(Map<String, Object> model,HttpServletRequest request) {

        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("authorize");
        modelAndView.addObject("clientId", authorizationRequest.getClientId());
        modelAndView.addObject("scopes", authorizationRequest.getScope());
        return modelAndView;
    }

}
