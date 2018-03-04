package com.rahmania.dto;

import java.io.Serializable;

public abstract  class AbstractDTO implements Serializable {

    public long id ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
