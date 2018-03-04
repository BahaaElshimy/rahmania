package com.rahmania;

import com.rahmania.dto.BaseResponse;
import com.rahmania.dto.LoginResponse;
import com.rahmania.dto.user.UserRegisterationDTO;
import com.rahmania.exception.RahmaniaException;
import com.rahmania.service.RegisterationService;
import com.rahmania.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * Created by bahaa on 29/01/18.
 */

@RestController
public class RegisterationController {

    @Autowired
    RegisterationService registerationService;

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public LoginResponse register(@Valid @RequestBody UserRegisterationDTO userRegisterationDTO , HttpSession session) throws UnsupportedEncodingException {
        registerationService.register(userRegisterationDTO ,session);
        return null;
    }

    @RequestMapping(value = "/validate" , method = RequestMethod.POST)
    public  ResponseEntity<BaseResponse> register(@RequestBody String token, HttpSession session)  throws RahmaniaException{
      return   new ResponseEntity<BaseResponse>( registerationService.validateToken(token, session), HttpStatus.OK);
    }
    @GetMapping(value = "validate/{mobile}")
    public ResponseEntity<Boolean> isExist(@PathVariable("mobile") String mobile) throws IOException {

        return new ResponseEntity<Boolean>( studentService.isExist(mobile), HttpStatus.OK);
    }

}
