package com.rahmania.controller.user;


import com.rahmania.entity.Student;
import com.rahmania.dto.user.UserDTO;
import com.rahmania.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {



    @Autowired
    UserService userService;


    @GetMapping(value = "/{id}")
    public Student findUser(@PathVariable("id") long id){
        return new Student();
    }


    //TODO get user form session not every time hit databse to get current user
    @GetMapping(value="/current")
    public UserDTO getCurrentUser(){

                     return        userService.retrieveUserByMobile(SecurityContextHolder.getContext().getAuthentication().getName());
    }



}
