package com.rahmania.controller.questioin;

import com.rahmania.dto.question.FillGapQuestionDTO;
import com.rahmania.dto.question.MultiChoiceQuestionDTO;
import com.rahmania.dto.question.QuestionDTO;
import com.rahmania.dto.question.WhatQuestionDTO;
import com.rahmania.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<QuestionDTO> getQuestion(@PathVariable("id") long id) {
        QuestionDTO question = questionService.getQuestionDTO(id);
        if (question == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<QuestionDTO>(question, HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/test")
    @ResponseBody
    public ResponseEntity<List< ? extends QuestionDTO>> listtestQuestions() throws IOException {
        List<? extends QuestionDTO> questions = questionService.listStudetnQuestions();
        return new ResponseEntity<List<? extends QuestionDTO>>(questions, HttpStatus.ACCEPTED);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<QuestionDTO>> listAllQuestions() {
        List<QuestionDTO> questions = questionService.listAll();
        return new ResponseEntity<List<QuestionDTO>>(questions, HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/multichoice/{tabId}")
    @ResponseBody
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> createMulitChoice(@RequestBody MultiChoiceQuestionDTO questionDTO , @PathVariable("tabId") Long tabId) throws Exception {
        questionService.saveMultiChoiceQuestion(questionDTO ,tabId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/fillGap/{tabId}")
    @ResponseBody
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> createFillGap(@RequestBody FillGapQuestionDTO questionDTO  ,@PathVariable("tabId") Long tabId) throws Exception {
        questionService.saveFillGapQuestion(questionDTO ,tabId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping(value = "/multichoice/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> updateMulitChoice(@PathVariable("id") Long id, @RequestBody MultiChoiceQuestionDTO questionDTO) throws Exception {
        questionService.updateMultiChoiceQuestion(questionDTO, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/fillGap/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> updateFillGap(@RequestBody FillGapQuestionDTO questionDTO , @PathVariable("id") Long id) throws Exception {
        questionService.updateFillGapQuestion(questionDTO ,id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> removeQuestion(@PathVariable("id") Long id) throws Exception {
        questionService.removeQuestion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/wQuestion/{tabId}")
    @ResponseBody
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> createWhatQuesiton(@RequestBody WhatQuestionDTO questionDTO  ,@PathVariable("tabId") Long tabId) throws Exception {
        questionService.saveWhatQuestion(questionDTO ,tabId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/wQuestion/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> updateWhatQuestion(@RequestBody WhatQuestionDTO questionDTO , @PathVariable("id") Long id) throws Exception {
        questionService.updateWhatQuestion(questionDTO ,id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/endTest")
    @ResponseBody
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> endTest() throws Exception {
        questionService.endTest();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
