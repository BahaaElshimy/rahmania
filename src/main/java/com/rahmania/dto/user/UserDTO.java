package com.rahmania.dto.user;

import com.rahmania.dto.AbstractDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO extends AbstractDTO {

    @NotNull(message = "الاسم لايجب ان يكون فارغا")
    private String name;

    @NotNull(message = "رقم الجوال لايمكن ان يكون فاغا")
    @Size(min=10)
    private String mobileNumber;

    private String email;

    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
