package com.rahmania.entity;

import javax.persistence.*;

@Entity
@DiscriminatorValue("multichoice")
public class MultiChoiceQuestion  extends Question {

    @Transient
    private String type = "multichoice";

    public String getType() {
        return type;
    }
}