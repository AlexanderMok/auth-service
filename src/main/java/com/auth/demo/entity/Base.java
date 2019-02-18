package com.auth.demo.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author Alexander Mok
 * @since 2019/01/28
 */
@MappedSuperclass
public class Base {
    @Column(name = "status")
    protected Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
