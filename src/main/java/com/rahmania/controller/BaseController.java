package com.rahmania.controller;

import com.rahmania.model.AboutDTO;
import com.rahmania.model.ConstraintDTO;
import com.rahmania.model.PrizeDTO;
import com.rahmania.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class BaseController {

    @Autowired
    SettingService settingService;
  @GetMapping
    public String getHome() {

        return "home";
    }

    @GetMapping("/login")
    public String welcome() {

        return "home";
    }
    @GetMapping("/prizes")
    public ResponseEntity<List<PrizeDTO>> retrivePrizes() {
        return new ResponseEntity<List<PrizeDTO>>(settingService.retrievePrizes(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/constraints")
    public ResponseEntity<List<ConstraintDTO>> retriveConstraints() {
        return new ResponseEntity<List<ConstraintDTO>>(settingService.retrieveConstraints(), HttpStatus.ACCEPTED);
    }



    @GetMapping("/about")
    public ResponseEntity<AboutDTO> retriveAbout() {
        return new ResponseEntity<AboutDTO>(settingService.getAbout(), HttpStatus.ACCEPTED);
    }

/*    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public String login(){
        return "login";
    }*/

}
