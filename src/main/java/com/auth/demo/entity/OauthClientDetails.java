package com.auth.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Alexander Mok
 * @since 2019/01/29
 */
@Entity
@Table(name = "oauth_client_details")
public class OauthClientDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "client_id")
    private String clientId;
    @Column(name = "resource_ids")
    private String resourceIds;
    @Column(name = "client_secret")
    private String clientSecret;
    @Column(name = "scope")
    private String scope;
    @Column(name = "authorized_grant_type")
    private String authorizedGrantType;
    @Column(name = "web_server_redirect_url")
    private String webServerRedirectUrl;
    @Column(name = "authorities")
    private String authorities;
    @Column(name = "access_token_validity")
    private Integer accessTokenValidity;
    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValidity;
    @Column(name = "additional_information")
    private String additionalInformation;
    @Column(name = "autoapprove")
    private String autoApprove;
    @Column(name = "status")
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAuthorizedGrantType() {
        return authorizedGrantType;
    }

    public void setAuthorizedGrantType(String authorizedGrantType) {
        this.authorizedGrantType = authorizedGrantType;
    }

    public String getWebServerRedirectUrl() {
        return webServerRedirectUrl;
    }

    public void setWebServerRedirectUrl(String webServerRedirectUrl) {
        this.webServerRedirectUrl = webServerRedirectUrl;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getAutoApprove() {
        return autoApprove;
    }

    public void setAutoApprove(String autoApprove) {
        this.autoApprove = autoApprove;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
