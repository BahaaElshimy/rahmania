package com.rahmania.dto;

/**
 * Created by bahaa on 27/01/18.
 */
public class LoginResponse {

    private int responseCode;

    private String responseMessage;


    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
