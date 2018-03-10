package com.rahmania.entity;


import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private DateTime creationDate;

    private DateTime modifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getCreationDate() {
        return creationDate;
    }

    @PrePersist
    public void setCreationDate() {
        this.creationDate =new DateTime();
    }

    public DateTime getModifiedDate() {
        return modifiedDate;
    }

    @PreUpdate
    public void setModifiedDate() {
        this.modifiedDate = new DateTime();
    }
}
