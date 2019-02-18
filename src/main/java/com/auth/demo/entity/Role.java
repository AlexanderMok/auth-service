package com.auth.demo.entity;

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
import java.util.Collections;
import java.util.List;

/**
 * @author Alexander Mok
 * @since 2019/01/24
 */
@Entity
@Table(name = "sys_role")
public class Role extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    protected Integer roleId;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_description")
    private String roleDescription;

    @Transient
    @ManyToMany
    @JoinTable(
            name = "sys_user_role_mapping",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> userList = Collections.emptyList();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "sys_role_authority_mapping",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")})
    private List<Authority> authorityList = Collections.emptyList();

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Authority> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(List<Authority> authorityList) {
        this.authorityList = authorityList;
    }
}
