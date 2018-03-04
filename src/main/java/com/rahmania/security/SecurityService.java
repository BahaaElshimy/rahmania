package com.rahmania.security;

/**
 * Created by bahaa on 27/01/18.
 */
public interface SecurityService {

    String findLoggedInMobilenumber();

    void autologin(String mobilenumber, String password);
}
