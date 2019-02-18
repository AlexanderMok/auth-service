package com.auth.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @author Alexander Mok
 * @since 2019/01/29
 */
@Entity
@Table(name = "sys_user_group")
public class UserGroup extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_description")
    private String groupDescription;

    @Column(name = "parent_group_id")
    private Integer parentGroupId;

    @Transient
    @ManyToMany
    @JoinTable(
            name = "sys_user_group_mapping",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> userList;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public Integer getParentGroupId() {
        return parentGroupId;
    }

    public void setParentGroupId(Integer parentGroupId) {
        this.parentGroupId = parentGroupId;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
