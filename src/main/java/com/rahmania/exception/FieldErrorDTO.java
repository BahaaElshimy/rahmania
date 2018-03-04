package com.rahmania.exception;

/**
 * Created by bahaa on 26/02/18.
 */
public class FieldErrorDTO {

    private String name;
    private String message;

    public FieldErrorDTO(){}
    public FieldErrorDTO(String name , String message){
        this.name = name;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
