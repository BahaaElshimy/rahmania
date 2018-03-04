package com.rahmania.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Role extends AbstractEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany
     @JoinTable(name = "role_menue" , joinColumns = {@JoinColumn(name = "role_id")} , inverseJoinColumns = {@JoinColumn(name = "menue_id")})
    Set<Menue> menueSet = new HashSet<>();

    public Set<Menue> getMenueSet() {
        return menueSet;
    }

    public void setMenueSet(Set<Menue> menueSet) {
        this.menueSet = menueSet;
    }
}
