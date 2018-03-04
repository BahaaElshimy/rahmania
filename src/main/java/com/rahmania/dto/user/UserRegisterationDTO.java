package com.rahmania.dto.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterationDTO extends UserDTO {

    @NotNull( message = "كلمة المرور لايمكن ان تكون فارغة ")
    @Size(min = 8 , message = "كلمة المرور على الاقل 8 احرف")
    public String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
