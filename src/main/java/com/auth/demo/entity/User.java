package com.auth.demo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represent User in db table for user information authentication.
 *
 * @author Alexander Mok
 * @since 2018/12/22
 */
@Entity
@Table(name = "sys_user")
public class User extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "sys_user_role_mapping",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roleList = Collections.emptyList();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "bss_user_customer_mapping",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_id")})
    private List<Customer> customerList = Collections.emptyList();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "sys_user_group_mapping",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id")})
    private List<UserGroup> userGroupList = Collections.emptyList();

    @Transient
    private Collection<GrantedAuthority> grantedAuthoritiesList = Collections.emptyList();

    public Collection<GrantedAuthority> getGrantedAuthoritiesList() {
        return getRoleList()
                .stream()
                .flatMap(role -> role.getAuthorityList().stream())
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<UserGroup> getUserGroupList() {
        return userGroupList;
    }

    public void setUserGroupList(List<UserGroup> userGroupList) {
        this.userGroupList = userGroupList;
    }
}
