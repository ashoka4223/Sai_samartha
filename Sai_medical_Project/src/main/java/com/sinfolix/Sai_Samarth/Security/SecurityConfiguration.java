package com.sinfolix.Sai_Samarth.Security;

import com.sinfolix.Sai_Samarth.service.Impl.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.internal.CustomizerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Autowired
    private final CustomUserDetailsServiceImpl userDetailsService;

    public SecurityConfiguration(CustomUserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(authorize -> {
                authorize.requestMatchers("/login","api/register").permitAll();
                authorize.anyRequest().fullyAuthenticated();
                })
                .formLogin(auth->{
                    auth.loginProcessingUrl("/api/login");
                    auth.defaultSuccessUrl("/api/home",true);
                })
                .oauth2Login(oauth2login -> {
                oauth2login.successHandler(( request,  response,  authentication) -> response.sendRedirect("/home"));
                })
                 .oauth2Login(oauth2login -> {
                     oauth2login.defaultSuccessUrl("/loginSuccess",true);
                 })
                .sessionManagement(session->{
                    session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
                })
                .rememberMe(remember->remember.disable())
                .logout(logout -> logout
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
        )
//                .exceptionHandling(exception->{
//                   exception.authenticationEntryPoint((request, response, authException) -> {
//                       response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                       response.getWriter().write("Unauthorized: Authentication token required.");
//                   });
//                })
                 .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}