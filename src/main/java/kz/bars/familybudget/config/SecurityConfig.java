package kz.bars.familybudget.config;

import kz.bars.familybudget.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@RequiredArgsConstructor
@Log4j2
public class SecurityConfig {

    private final UserServiceImpl userService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userService)
                .passwordEncoder(passwordEncoder());

        http.exceptionHandling()
                .accessDeniedPage("/forbidden");

        http.formLogin()
                .loginPage("/signin") //page of login
                .loginProcessingUrl("/auth") //<form action = 'auth'>
                .failureUrl("/signin?error") //if incorrect email or password
                .usernameParameter("user_email") //<input type = 'email' name = 'user_email'>
                .passwordParameter("user_password") //<input type = 'password' name = 'user_password'>
                .successHandler((req, res, auth) -> {
                    // add your custom logging here
                    log.info("!User " + auth.getName() + " has logged in successfully");
                    // redirect to the profile page after successful authentication
                    res.sendRedirect("/profile");
                });

        http.logout()
                .logoutUrl("/logout") //<form action = 'logout' method = 'post'>
                .logoutSuccessHandler(logoutSuccessHandler()); // add your logout success handler here

        http.csrf().disable(); //ban on post requests

        return http.build();
    }

    @Bean
    LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {
            // add your logging code here
            log.info("!User " + authentication.getName() + " has logged out");
            response.sendRedirect("/signin");
        };
    }
}
