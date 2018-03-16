package com.rahmania.service.user;

import com.rahmania.dto.user.UserDTO;
import com.rahmania.dto.user.UserRegisterationDTO;
import com.rahmania.entity.User;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {

    UserDTO saveUser(UserRegisterationDTO user) throws UnsupportedEncodingException;

    List<UserDTO> listUsers();

    UserDTO retriveUser(Long id);

    UserDTO retrieveUserByMobile(String mobile);

    User getUSer(String mobile);
}
