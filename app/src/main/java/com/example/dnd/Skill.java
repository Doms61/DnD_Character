package com.example.dnd;

import java.io.Serializable;

public class Skill implements Serializable {

    String name, description;

    public Skill(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Skill() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
