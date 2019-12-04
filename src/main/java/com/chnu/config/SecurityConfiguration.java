package com.chnu.config;

import com.chnu.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.chnu")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String[] WHITE_LIST_URLS = {"/user/login", "/user/logout", "/user/register",
            "/user/register/confirm", "/test/**"};
    private static final String[] WHITE_LIST_VIEWS = {"/", "/login", "/registration"};
    private static final String[] RESOURCE_URLS = {"/resources/**"};

    @Autowired
    private UserService userService;

    @Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()

                .csrf().disable()

                .authorizeRequests()

                .antMatchers(Stream.concat(
                                Stream.concat(Arrays.stream(WHITE_LIST_URLS), Arrays.stream(WHITE_LIST_VIEWS)),
                                Arrays.stream(RESOURCE_URLS))
                        .toArray(String[]::new))
                .permitAll()

                .anyRequest()
                .authenticated()
                .and()

                .formLogin().failureForwardUrl("/login").loginPage("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }
}
