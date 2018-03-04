package com.rahmania.sms;

import java.io.UnsupportedEncodingException;

/**
 * Created by bahaa on 03/03/18.
 */
public interface SmsService {

    void sendMessage(String userName, String password, String sender, String message, String numbers) throws UnsupportedEncodingException;
}
