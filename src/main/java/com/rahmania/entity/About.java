package com.rahmania.entity;

import javax.persistence.Entity;

/**
 * Created by bahaa on 10/02/18.
 */
@Entity
public class About  extends AbstractEntity{

    private String about;

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
