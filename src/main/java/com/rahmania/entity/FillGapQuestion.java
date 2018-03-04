package com.rahmania.entity;

import javax.persistence.*;

@Entity
@DiscriminatorValue("fillGap")
public class FillGapQuestion extends Question {

    @Transient
    private String type = "fillGap";

    public String getType() {
        return type;
    }
}
