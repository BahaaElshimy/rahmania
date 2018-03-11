package com.rahmania.dto;

import com.rahmania.service.StudentService;
import com.rahmania.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by bahaa on 04/02/18.
 */
@RestController
@RequestMapping(value = "api/studnet")
public class StudentController {

    @Autowired
    SubjectService subjectService;

    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity<String> submittAnswers(@RequestBody List<StudentAnswersDTO> answersDTOS) {
        studentService.correctAnswer(answersDTOS);
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<String> saveAnswers(@RequestBody String answersDTOS) {
        studentService.saveStudentAnswer(answersDTOS);
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }


    @GetMapping
    public ResponseEntity<List<ExamSubjectDTO>> loadExam() {
        return new ResponseEntity<List<ExamSubjectDTO>>(subjectService.loadExam(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<StudentDTO>> listAll() {
        return new ResponseEntity<List<StudentDTO>>(studentService.retriveAllSutdents(), HttpStatus.ACCEPTED);
    }


    @PutMapping("/eleminate")
    public ResponseEntity<String> eleminateStudnts(@RequestBody List<Long> studentIs , @RequestParam(value = "all", required = false) Boolean all) {
        studentService.eleminateStudnts(studentIs ,all);
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }


    @PutMapping("/partcipate")
    public ResponseEntity<String> moveToParticipate(@RequestBody List<Long> studentIs, @RequestParam(value = "all", required = false) Boolean all) {
        studentService.moveToParticipate(studentIs, all);
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }


    @GetMapping(value = "/partcipated")
    public ResponseEntity<List<StudentDTO>> retrivePartcipated() {
        return new ResponseEntity<List<StudentDTO>>(studentService.retrivePartcipated(), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/answers/{id}")
    public ResponseEntity<List<StudentAnswersDTO>> retriveStudentAnswers(@PathVariable("id") Long studentId) throws IOException {
        return new ResponseEntity<List<StudentAnswersDTO>>(studentService.retrieveStudentAnswers(studentId), HttpStatus.ACCEPTED);
    }
    @PutMapping("/candidate")
    public ResponseEntity<String> moveToCandidate(@RequestBody List<Long> studentIs, @RequestParam(value = "all", required = false) Boolean all) {
        studentService.moveToCandidate(studentIs, all);
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }


    @GetMapping(value = "/candidate")
    public ResponseEntity<List<StudentDTO>> retriveCandidate() {
        return new ResponseEntity<List<StudentDTO>>(studentService.retriveCandidate(), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/eliminated")
    public ResponseEntity<List<StudentDTO>> retriveEliminated() {
        return new ResponseEntity<List<StudentDTO>>(studentService.retriveEliminated(), HttpStatus.ACCEPTED);
    }


    @GetMapping(value = "/search/eliminated/{grade}")
    public ResponseEntity<List<StudentDTO>> getElminatedByGrade( @PathVariable Long grade) {
        return new ResponseEntity<List<StudentDTO>>(studentService.getElminatedByGrade(grade), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/search/candidate/{grade}")
    public ResponseEntity<List<StudentDTO>> getCandidateByGrade( @PathVariable Long grade) {
        return new ResponseEntity<List<StudentDTO>>(studentService.getCandidateByGrade(grade), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/search/all/{grade}")
    public ResponseEntity<List<StudentDTO>> getAllByGrade( @PathVariable Long grade) {
        return new ResponseEntity<List<StudentDTO>>(studentService.getAllByGrade(grade), HttpStatus.ACCEPTED);
    }
    @GetMapping(value = "/search/partcipated/{grade}")
    public ResponseEntity<List<StudentDTO>> getPartcipatedByGrade( @PathVariable Long grade) {
        return new ResponseEntity<List<StudentDTO>>(studentService.getPartcipatedByGrade(grade), HttpStatus.ACCEPTED);
    }



    @GetMapping("/listAllTookExam")
    public ResponseEntity<List<StudentDTO>> listAllTookExam() {
        return new ResponseEntity<List<StudentDTO>>(studentService.retriveAllSutdentsTookExam(), HttpStatus.ACCEPTED);
    }
}
