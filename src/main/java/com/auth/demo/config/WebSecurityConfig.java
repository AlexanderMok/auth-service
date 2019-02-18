package com.auth.demo.config;

import com.auth.demo.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import javax.servlet.http.HttpServletResponse;

/**
 * Spring Security configuration.
 *
 * @author Alexander Mok
 * @since 2018/12/17
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    /**
     * set {@code preAuthenticatedAuthenticationProvider} for Refresh Token
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider =
                new PreAuthenticatedAuthenticationProvider();
        UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken>
                userDetailsByNameServiceWrapper =
                new UserDetailsByNameServiceWrapper<>(userDetailsService());
        preAuthenticatedAuthenticationProvider.setPreAuthenticatedUserDetailsService(
                userDetailsByNameServiceWrapper);
        // @formatter:off
        auth.authenticationProvider(preAuthenticatedAuthenticationProvider)
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
        // @formatter:on
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // @formatter:off
        httpSecurity
                .csrf().disable()
                .logout().disable()
                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(
                        (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();
        // @formatter:on
    }
}
