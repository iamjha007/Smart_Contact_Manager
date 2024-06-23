package com.springlearn.SCM.configs;

import com.springlearn.SCM.entity.Providers;
import com.springlearn.SCM.entity.User;
import com.springlearn.SCM.misc.AppConstants;
import com.springlearn.SCM.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.info("user logged in successfully");

        DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) authentication.getPrincipal();


        defaultOAuth2User.getAttributes().forEach((key, value) -> {
            log.info(key + " : " + value);
        });
        log.info(defaultOAuth2User.getAuthorities().toString());

        User user = User
                .builder()
                .id(UUID.randomUUID().toString())
                .name(defaultOAuth2User.getAttribute("name").toString())
                .email(defaultOAuth2User.getAttribute("email").toString())
                .profilePic(defaultOAuth2User.getAttribute("picture").toString())
                .password("password")
                .provider(Providers.GOOGLE)
                .providerId(defaultOAuth2User.getName())
                .enabled(true)
                .emailVerified(true)
                .rolesList(List.of(AppConstants.ROLE_USER))
                .about("This account is created by Google authentication")
                .build();

        if(userRepository.findByEmail(defaultOAuth2User.getAttribute("email").toString()).orElse(null) == null){

            userRepository.save(user);
            log.info("user registered successfully"+user);
        }

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
    }
}
