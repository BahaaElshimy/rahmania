package com.rahmania.entity;

import javax.persistence.Entity;

/**
 * Created by bahaa on 10/02/18.
 */

@Entity
public class Prize extends AbstractEntity {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
