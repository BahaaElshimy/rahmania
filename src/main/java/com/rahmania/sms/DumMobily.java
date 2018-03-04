package com.rahmania.sms;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * Created by bahaa on 03/03/18.
 */
@Profile("dev")
@Component
public class DumMobily implements SmsService {
    @Override
    public void sendMessage(String userName, String password, String sender, String message, String numbers) throws UnsupportedEncodingException {

    }
}
