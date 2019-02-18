package com.auth.demo.entity;

/**
 * Represent user information in Spring security framework. Note that this one is different from the
 * one that represents user from db table.
 *
 * @author Alexander Mok
 * @since 2018/12/22
 */
public class SecurityUserDetails extends org.springframework.security.core.userdetails.User {

  public SecurityUserDetails(com.auth.demo.entity.User userEntity) {
    super(
        userEntity.getUsername(), userEntity.getPassword(), userEntity.getGrantedAuthoritiesList());
  }
}
