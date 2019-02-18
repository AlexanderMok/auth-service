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
 * @since 2019/01/28
 */
@Entity
@Table(name = "sys_authority")
public class Authority extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    protected Integer authorityId;

    @Column(name = "authority_name")
    private String authorityName;

    @Column(name = "authority_description")
    private String authorityDescription;

    @Transient
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "sys_role_authority_mapping",
            joinColumns = {@JoinColumn(name = "authority_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roleList = Collections.emptyList();

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getAuthorityDescription() {
        return authorityDescription;
    }

    public void setAuthorityDescription(String authorityDescription) {
        this.authorityDescription = authorityDescription;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
