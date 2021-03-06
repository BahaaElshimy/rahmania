package com.rahmania.service;

import com.rahmania.dto.StudentAnswersDTO;
import com.rahmania.dto.StudentDTO;

import java.io.IOException;
import java.util.List;

/**
 * Created by bahaa on 07/02/18.
 */
public interface StudentService {

    void correctAnswer(List<StudentAnswersDTO> questions);

    List<StudentDTO> retriveAllSutdents();

    void eleminateStudnts(List<Long> studentIs, Boolean all);

    void moveToParticipate(List<Long> studentIs ,Boolean all);


    List<StudentDTO> retrivePartcipated();

    void saveStudentAnswer(String answersDTOS) ;

    List<StudentAnswersDTO> retrieveStudentAnswers(Long studentId) throws IOException;

    Boolean isExist(String mobile);

    void moveToCandidate(List<Long> studentIs, Boolean all);

    List<StudentDTO> retriveCandidate();

    List<StudentDTO> retriveEliminated();

    List<StudentDTO> getElminatedByGrade(Long grade);

    List<StudentDTO> getCandidateByGrade(Long grade);

    List<StudentDTO> getAllByGrade(Long grade);

    List<StudentDTO> getPartcipatedByGrade(Long grade);

    List<StudentDTO> retriveAllSutdentsTookExam();
}
