package com.vtn.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.vtn.enums.UserRole;
import com.vtn.services.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Order(2)
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
        "com.vtn.components",
        "com.vtn.controllers",
        "com.vtn.repository",
        "com.vtn.services"
})
public class WebSecurityConfigs extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(@NotNull AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(this.passwordEncoder());
    }

    @Override
    protected void configure(@NotNull HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/", "/admin/**").hasRole(UserRole.ROLE_ADMIN.alias())
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .usernameParameter("username").passwordParameter("password")
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    userService.updateLastLogin(authentication.getName());

                    boolean isAdmin = authentication.getAuthorities().parallelStream()
                            .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(UserRole.ROLE_ADMIN.name()));

                    if (isAdmin) {
                        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");
                    } else {
                        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/logout");
                    }
                }).failureUrl("/login?error=true").permitAll()
                .and()
                .logout().logoutSuccessUrl("/login").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/login?accessDenied=true")
                .and();

        http.csrf().disable();
    }

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name", "dage0bjr6",
                        "api_key", "199369517429492",
                        "api_secret", "A9ysVaH6hZVGMHKCUeTNCx6P9EY",
                        "secure", true
                )
        );
    }
}
