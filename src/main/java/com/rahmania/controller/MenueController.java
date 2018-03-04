package com.rahmania.controller;

import com.rahmania.dto.MenueDTO;
import com.rahmania.service.MenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by bahaa on 30/01/18.
 */


@RestController
@RequestMapping("/menues")
public class MenueController {

    @Autowired
    MenueService menueService;

    @GetMapping
    public List<MenueDTO> retriveMenues(){
      return     menueService.loadUserMenue();
    }
}
