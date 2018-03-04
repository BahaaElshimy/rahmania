package com.rahmania.service;

import com.rahmania.dto.BaseResponse;
import com.rahmania.dto.user.UserRegisterationDTO;
import com.rahmania.entity.Users;
import com.rahmania.exception.RahmaniaException;
import com.rahmania.security.SecurityService;
import com.rahmania.service.user.UserService;
import com.rahmania.sms.SMSSender;
import com.rahmania.util.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

/**
 * Created by bahaa on 29/01/18.
 */
@Service
@Transactional
public class RegisterationServiceImpl implements RegisterationService {

    @Autowired
    Transformer transformer;

    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;


    @Override
    public void register(UserRegisterationDTO dto, HttpSession session) throws UnsupportedEncodingException {
        userService.saveUser(dto);
        session.setAttribute("user", dto.getMobileNumber());
        session.setAttribute("password", dto.getPassword());
    }


    @Override
    public BaseResponse validateToken(String token, HttpSession session) throws RahmaniaException {
        BaseResponse response = new BaseResponse();
        Objects.requireNonNull(session.getAttribute("user"));
        Objects.requireNonNull(session.getAttribute("password"));
        Users user = userService.getUSer((String) session.getAttribute("user"));
        if (user.getToken().equals(token)) {
            user.setActive(true);
            securityService.autologin(user.getMobileNumber(), (String) session.getAttribute("password"));
        } else {
            response.setReplyCode(-5);
        }
        return response;
    }

}
