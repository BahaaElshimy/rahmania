package com.rahmania.service;

import com.rahmania.dto.BaseResponse;
import com.rahmania.dto.user.UserRegisterationDTO;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * Created by bahaa on 29/01/18.
 */
public interface RegisterationService {

    void register(UserRegisterationDTO dto, HttpSession session) throws UnsupportedEncodingException;
    BaseResponse validateToken(String token, HttpSession session);

}
