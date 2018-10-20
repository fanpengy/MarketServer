package com.market.model;

import com.db.file.model.BaseModel;

import java.io.Serializable;

public class Person extends BaseModel implements Serializable{
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
