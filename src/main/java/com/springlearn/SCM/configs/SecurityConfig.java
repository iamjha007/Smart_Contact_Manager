package com.springlearn.SCM.configs;

import com.springlearn.SCM.service.impl.SecurityCustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    //user create and login usign java code with in memory service

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails user1 = User
//                .withDefaultPasswordEncoder()
//                .username("admin")
//                .password("admin")
//                .roles("ADMIN")
//                .build();
//        UserDetails user2 = User
//                .withDefaultPasswordEncoder()
//                .username("user")
//                .password("user")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user1, user2);
//    }

    @Autowired
    private SecurityCustomUserDetailService userDetailsService;
    @Autowired
    private OAuthAuthenticationSuccessHandler oAuthAuthenticationSuccessHandler;
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorize->{

            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });
        //form default login
        //we will edit here whenever we want to change anything related to form login
        http.formLogin(formLogin->{
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
// use defaultSuccessUrl instead of successForwardUrl in your security configuration.
//The defaultSuccessUrl will perform a redirect (HTTP 302) to the specified URL after a successful login,
//which changes the request method to GET.
            formLogin.defaultSuccessUrl("/user/dashboard");
            formLogin.failureUrl("/login?error=true");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");
        });

        http.csrf(AbstractHttpConfigurer::disable);
        //congigure logout
        //if csrf token is enabled then we have to disable it cuz the logout request will not work as it requires post method
        //but we are giving get method in logout form
        http.logout(logoutForm->{
            logoutForm.logoutUrl("/logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");
        });

        //oauth configrations
        http.oauth2Login(oauth->{
            oauth.loginPage("/login");
            oauth.successHandler(oAuthAuthenticationSuccessHandler);

        });
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
