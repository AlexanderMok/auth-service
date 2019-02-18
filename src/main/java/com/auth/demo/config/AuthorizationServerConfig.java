package com.auth.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;

/**
 * @author Alexander Mok
 * @since 2019/01/24
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private String publicKey = "publicKey";

    private String privateKey = "privateKey";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClientDetailsService clientDetailsService;

    private PasswordEncoder passwordEncoder;

    private DataSource dataSource;

    public AuthorizationServerConfig(DataSource dataSource, PasswordEncoder passwordEncoder) throws Exception {
        this.dataSource = dataSource;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Defines the security constraints on the token endpoint. "isAnonymous() ||
     * hasAuthority('ROLE_TRUSTED_CLIENT')"
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // @formatter:off
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
        // @formatter:on
    }

    /**
     * A configurer that defines the client details service. If use jdbc to load clients configs,
     * apply schema on <a
     * href="https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security
     * -oauth2/src/test/resources/schema.sql">Spring oauth2 schema<a/>
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // @formatter:off
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
        // @formatter:on
    }

    /**
     * Defines the authorization and token endpoints and the token services.
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // @formatter:off
        endpoints
                .tokenServices(authorizationServerTokenServices())
                .accessTokenConverter(accessTokenConverter())
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager);
        // @formatter:on
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * Encode default UUID token to JWT token Can override {@code JwtAccessTokenConverter.enhance()}
     * to add additional info in token
     *
     * @return JwtAccessTokenConverter
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(privateKey);
        return jwtAccessTokenConverter;
    }

    @Bean
    public AuthorizationServerTokenServices authorizationServerTokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setReuseRefreshToken(false);
        defaultTokenServices.setTokenEnhancer(accessTokenConverter());
        defaultTokenServices.setAuthenticationManager(authenticationManager);
        defaultTokenServices.setClientDetailsService(jdbcClientDetailsService());
        return defaultTokenServices;
    }

    @Bean
    public JdbcClientDetailsService jdbcClientDetailsService() {
        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        jdbcClientDetailsService.setPasswordEncoder(passwordEncoder);
        return jdbcClientDetailsService;
    }
}
