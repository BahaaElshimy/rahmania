package com.rahmania.dto;

/**
 * Created by bahaa on 04/03/18.
 */
public class BaseResponse {

    private int replyCode;

    private String replyMessage;

    public int getReplyCode() {
        return replyCode;
    }

    public void setReplyCode(int replyCode) {
        this.replyCode = replyCode;
    }

    public String getReplyMessage() {
        return replyMessage;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
    }
}
