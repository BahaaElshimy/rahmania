package com.rahmania.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahmania.dto.SubjectDTO;
import com.rahmania.dto.question.QuestionDTO;
import com.rahmania.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/subjects")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<SubjectDTO> getSubject(@PathVariable("id") long id) {
        SubjectDTO subject = subjectService.getSubject(id);
        if (subject == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<SubjectDTO>(subject, HttpStatus.ACCEPTED);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<SubjectDTO>> listAllSubjects() {
        List<SubjectDTO> subjects = subjectService.listAll();
        return new ResponseEntity<List<SubjectDTO>>(subjects, HttpStatus.ACCEPTED);
    }


    @PostMapping
    @ResponseBody
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> createSubject(@RequestParam("file") MultipartFile file, @RequestParam("subject") String json, HttpServletRequest request) throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SubjectDTO subjectDTO = mapper.readValue(json, SubjectDTO.class);
            subjectService.saveSubject(subjectDTO, file, request);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    @PutMapping(value = "/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> updateSubject(@PathVariable("id")Long id , @RequestBody SubjectDTO subjectDTO, @RequestParam(value = "file" , required = false) MultipartFile file,HttpServletRequest request) throws Exception {
         subjectService.updateSubject(subjectDTO , file, request ,id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> deleteSubject(@PathVariable("id") Long id) throws Exception {
        subjectService.deleteSubject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/questions/{id}")
    public ResponseEntity<List<QuestionDTO>> listAllSubjectQuestions(@PathVariable("id") Long id) {
        List<QuestionDTO> subjects = subjectService.retrieveSubjectQuestions(id);
        return new ResponseEntity<List<QuestionDTO>>(subjects, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "video/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> deleteSubjectVideo(@PathVariable("id") Long id) throws Exception {
        subjectService.removeSubjectVideo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
