package com.rahmania.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahmania.dto.StudentAnswersDTO;
import com.rahmania.dto.StudentDTO;
import com.rahmania.entity.Student;
import com.rahmania.repository.QuestionRepository;
import com.rahmania.repository.StudentRepository;
import com.rahmania.repository.UserRepository;
import com.rahmania.security.SecurityHelper;
import com.rahmania.service.question.QuestionService;
import com.rahmania.util.AppConstants;
import com.rahmania.util.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by bahaa on 07/02/18.
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionService questionService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Transformer transformer;


    @Autowired
    StudentRepository studentRepository;

    @Override
    public void correctAnswer(List<StudentAnswersDTO> questionAnswers) {
        try {
            logger.info("****** start correct answers ********");
            Long grade = 0L;
            Map<Long, Long> rightAnswerDTOS = questionRepository.getRightAnswers().stream().collect(Collectors.toMap(e -> e.getQuestionId(), e -> e.getAnswerId()));
            for (StudentAnswersDTO questionAnswer : questionAnswers) {
                if (questionAnswer.getType().toLowerCase().equals(AppConstants.MULTI_CHOICE)) {
                    if (rightAnswerDTOS.get(questionAnswer.getId()) != null && rightAnswerDTOS.get(questionAnswer.getId()).equals(questionAnswer.getAnswerId()))
                        grade++;
                }
            }

            Student currentStudent = (Student) userRepository.findByMobileNumber(SecurityHelper.getCurrentUser());
            currentStudent.setGrade(grade);
            ObjectMapper mapper = new ObjectMapper();
            currentStudent.setStudentSavedAnswers(mapper.writeValueAsString(questionAnswers));
            logger.info("****** end correct answers ********");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<StudentDTO> retriveAllSutdents() {
        try {
            return transformer.transform(studentRepository.findAll(), StudentDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void eleminateStudnts(List<Long> studentIds) {
        studentRepository.eleminateStudnts(studentIds);
    }

    @Override
    public void moveToParticipate(List<Long> studentIds, Boolean all) {

        if (Objects.nonNull(studentIds) && Objects.nonNull(all) && all) {
            studentRepository.moveToAllStudentsTParticipate();
            return;
        }
        studentRepository.moveToParticipate(studentIds);
    }

    @Override
    public List<StudentDTO> retrivePartcipated() {
        return transformer.transform(studentRepository.findByParticipatedIsTrue(), StudentDTO.class);
    }

    @Override
    public void saveStudentAnswer(@RequestBody String answersDTOS) {
        Student currentStudent = (Student) userRepository.findByMobileNumber(SecurityHelper.getCurrentUser());
        currentStudent.setStudentSavedAnswers(answersDTOS);
    }

    @Override
    public List<StudentAnswersDTO> retrieveStudentAnswers(Long studentId) throws IOException {
        Student student = (Student) userRepository.findOne(studentId);

        ObjectMapper mapper = new ObjectMapper();
        List<StudentAnswersDTO> answersDTOS = mapper.readValue(student.getStudentSavedAnswers(),
                new TypeReference<ArrayList<StudentAnswersDTO>>() {
                });

        return answersDTOS;

    }

    @Override
    public Boolean isExist(String mobile) {
        Student student = (Student) userRepository.findByMobileNumber(mobile);
        return Objects.nonNull(student);
    }

    @Override
    public void moveToCandidate(List<Long> studentIs, Boolean all) {

        if (Objects.nonNull(studentIs) && Objects.nonNull(all) && all) {
            studentRepository.moveToAllStudentsTParticipate();
            return;
        }
        studentRepository.moveToCandidate(studentIs);
    }

    @Override
    public List<StudentDTO> retriveCandidate() {
        return transformer.transform(studentRepository.findByCandidateIsTrue(), StudentDTO.class);
    }
}