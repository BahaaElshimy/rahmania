package com.rahmania.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bahaa on 30/01/18.
 */
@Entity
public class Menue  extends  AbstractEntity{

    private String name;

    private String url;

     @Column( columnDefinition = "boolean default 'false'")
    private boolean disabled = false;

    @ManyToMany(mappedBy = "menueSet")
    private Set<Roles> roleSet = new HashSet<>();
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Roles> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Roles> roleSet) {
        this.roleSet = roleSet;
    }

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
    
    
}
