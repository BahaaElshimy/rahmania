package com.rahmania.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Created by bahaa on 27/02/18.
 */

@Entity
@DiscriminatorValue("wQuestion")
public class WhatQuestion extends Question {

    @Transient
    private String type = "wQuestion";

    public String getType() {
        return type;
    }
}
