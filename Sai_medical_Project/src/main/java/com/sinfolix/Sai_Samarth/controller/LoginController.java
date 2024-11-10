package com.sinfolix.Sai_Samarth.controller;


import com.sinfolix.Sai_Samarth.service.Impl.GoogleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin(origins = "/**")
public class LoginController {

    @Autowired
    private GoogleUserService userService;

    @GetMapping("/loginSuccess")
    public String loginSuccess(OAuth2AuthenticationToken authenticationToken, Model model){
        userService.processOAuthPostLogin(authenticationToken);
        return "Welcome to Sai Samartha Medical Store";
    }
    @GetMapping("/userinfo")
    public Map<String, Object> getFullUserInfo(@AuthenticationPrincipal OAuth2User oauthUser) {
        return oauthUser.getAttributes();
    }
}
