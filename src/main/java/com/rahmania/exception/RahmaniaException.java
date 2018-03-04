package com.rahmania.exception;

import java.time.LocalDateTime;

public class RahmaniaException extends RuntimeException {

    private String message;
    private LocalDateTime date;
    private String description;

    public RahmaniaException() {
        super();
    }


    public RahmaniaException(String message, String description) {
        super(message);
        this.message = message;
        this.description = description;
        this.date = LocalDateTime.now();
    }


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
