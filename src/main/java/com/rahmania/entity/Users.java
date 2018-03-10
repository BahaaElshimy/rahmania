package com.rahmania.entity;


import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy =InheritanceType.JOINED)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"mobileNumber"})})
public  class Users extends AbstractEntity {

    private String token;

    private boolean active;

    @Column(nullable = false  )
    private String name;

    @Column(unique = true , nullable = false)
    private String mobileNumber;

    private String email;

    @Column( nullable = false)
    private String password;

    @ManyToOne
    private Roles role ;

    private DateTime lastForgetPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public DateTime getLastForgetPassword() {
        return lastForgetPassword;
    }

    public void setLastForgetPassword(DateTime lastForgetPassword) {
        this.lastForgetPassword = lastForgetPassword;
    }
}
