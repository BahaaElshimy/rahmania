package com.rahmania.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by bahaa on 17/02/18.
 */
@Component
public class SMSSender {

    @Autowired
   SmsService smsService;


    public  void sendRegisterationMessage(String mobileNumber  , String token) throws UnsupportedEncodingException{
        smsService.sendMessage("966567926658" ,"pass1234"  ,"rahmanya" , token, mobileNumber);
    }

}
