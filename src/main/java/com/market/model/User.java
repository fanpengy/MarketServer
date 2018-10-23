package com.market.model;

import com.db.file.dao.BaseDao;
import com.db.file.model.BaseModel;

import java.io.Serializable;

public class User extends BaseModel implements Serializable {
    private String name;
    private Integer pwd;
    private Integer identity;//1是游客，2是商户

    // TODO: 2018/10/23 创建空构造器

    public User(String name, Integer pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public User(String name, Integer pwd, Integer identity) {
        this.name = name;
        this.pwd = pwd;
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPwd() {
        return pwd;
    }

    public void setPwd(Integer pwd) {
        this.pwd = pwd;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }
}
