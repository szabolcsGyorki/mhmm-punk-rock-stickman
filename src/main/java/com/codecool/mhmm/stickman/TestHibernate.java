package com.codecool.mhmm.stickman;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TestHibernate {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;
    private int value;

    long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }
}
