package com.rahmania.controller;

import com.rahmania.model.AboutDTO;
import com.rahmania.model.ConstraintDTO;
import com.rahmania.model.PrizeDTO;
import com.rahmania.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by bahaa on 10/02/18.
 */
@RestController
@RequestMapping("api/settings")
public class SettingController {

    @Autowired
    SettingService settingService;


    @PostMapping("/about")
    public ResponseEntity<String> createAbout(@RequestBody AboutDTO aboutDTO) throws Exception {
        settingService.AddAbout(aboutDTO);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @PutMapping("/about/{id}")
    public ResponseEntity<String> updateAbout(@RequestBody AboutDTO aboutDTO, @PathVariable("id") Long id) throws Exception {
        settingService.editAbout(aboutDTO, id);
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }



    @PostMapping("/prizes")
    public ResponseEntity<String> createPrize(@RequestBody PrizeDTO prizeDTO) throws Exception {
        settingService.addPrize(prizeDTO);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @PutMapping("/prizes/{id}")
    public ResponseEntity<String> updateAbout(@RequestBody PrizeDTO prizeDTO, @PathVariable("id") Long id) throws Exception {
        settingService.editPrize(prizeDTO, id);
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }



    @PostMapping("/constraints")
    public ResponseEntity<String> createRule(@RequestBody ConstraintDTO constraintDTO) throws Exception {
        settingService.addConstraing(constraintDTO);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @PutMapping("/constraints/{id}")
    public ResponseEntity<String> updateRule(@RequestBody ConstraintDTO constraintDTO, @PathVariable("id") Long id) throws Exception {
        settingService.editConstraing(constraintDTO, id);
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/uploadPrizeImage")
    public ResponseEntity<String> uploadPrizesImage(@RequestParam MultipartFile file , HttpServletRequest request) throws Exception {
        settingService.uploadPrizesImage(file ,request);
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @DeleteMapping("/constraints/{id}")
    public ResponseEntity<String> deleteRule( @PathVariable("id") Long id) throws Exception {
        settingService.deleteConstraing(id);
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/prizes/{id}")
    public ResponseEntity<String> deletePrize( @PathVariable("id") Long id) throws Exception {
        settingService.deletePrize(id);
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }

}
