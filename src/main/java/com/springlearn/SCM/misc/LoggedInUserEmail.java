package com.springlearn.SCM.misc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;


@Slf4j
public class LoggedInUserEmail {

    public static String getLoggedInUserEmail(Authentication authentication) {

        if (authentication instanceof OAuth2AuthenticationToken) {
            var OAuth2Token = (OAuth2AuthenticationToken) authentication;

            var clientId = OAuth2Token.getAuthorizedClientRegistrationId();

            var oAuth2User =(OAuth2User) OAuth2Token.getPrincipal();
            //sign in with google

            if (clientId.equals("google")) {

                log.info("Logged in with google");
                log.info(oAuth2User.getAttribute("email"));
                return oAuth2User.getAttribute("email");

            }else if (clientId.equals("facebook")) {
                log.info("Logged in with facebook");
                System.out.println("Logged in with facebook");
            } else if(clientId.equals("github")) {
                log.info("Logged in with github");
            }


        }else {
            return authentication.getName();
        }
        return "z5s5h@example.com";
    }
}
