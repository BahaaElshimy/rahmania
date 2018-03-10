package com.rahmania.service.question;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahmania.dto.StudentAnswersDTO;
import com.rahmania.dto.question.WhatQuestionDTO;
import com.rahmania.entity.*;
import com.rahmania.exception.RahmaniaException;
import com.rahmania.dto.question.FillGapQuestionDTO;
import com.rahmania.dto.question.MultiChoiceQuestionDTO;
import com.rahmania.dto.question.QuestionDTO;
import com.rahmania.repository.MenuRepository;
import com.rahmania.repository.QuestionRepository;
import com.rahmania.repository.SubjectRepository;
import com.rahmania.repository.UserRepository;
import com.rahmania.security.SecurityHelper;
import com.rahmania.service.MenueService;
import com.rahmania.util.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//TODO save method has dublicate code for 3 types
@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    Transformer transformer;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MenuRepository menuReposiroty;

    @Override
    public QuestionDTO getQuestionDTO(long id) {
        Question question = getQuestion(id);
        if (question == null) return null;
        return SimpleQuestionFactoryTransformer.convertQuestionToModel(question, transformer);
    }

    private Question getQuestion(long id) {
        Question question = questionRepository.findOne(id);
        if (Objects.isNull(question))
            return null;
        return question;
    }

    @Override
    public List<QuestionDTO> listAll() {
        List<QuestionDTO> result = new ArrayList<>();
        List<Question> questions = questionRepository.getAllQuestions();
        questions.forEach(e -> {
            result.add(SimpleQuestionFactoryTransformer.convertQuestionToModel(e, transformer));
        });
        return result;
    }

    @Override
    public void saveMultiChoiceQuestion(MultiChoiceQuestionDTO questionDTO, Long tabId) throws Exception {
        MultiChoiceQuestion question = transformer.transform(questionDTO, MultiChoiceQuestion.class);
        Subject subject = getSubject(tabId);

        question.getAnswers().forEach(e -> {
            e.setQuestion(question);
        });

        question.setSubject(subject);
        questionRepository.save(question);
    }

    private Subject getSubject(Long tabId) {
        Subject subject = subjectRepository.findOne(tabId);
        if (Objects.isNull(subject))
            throw new IllegalArgumentException("subject can't be null");
        return subject;
    }

    @Override
    public void saveFillGapQuestion(FillGapQuestionDTO questionDTO, Long tabId) throws Exception {
        Subject subject = getSubject(tabId);
        FillGapQuestion question = transformer.transform(questionDTO, FillGapQuestion.class);
        question.setSubject(subject);
        questionRepository.save(question);
    }

    @Override
    public void updateMultiChoiceQuestion(MultiChoiceQuestionDTO questionDTO, Long id) throws Exception {
        Question saveQuestion = getQuestion(id);
        saveMultiChoiceQuestion(questionDTO, saveQuestion.getSubject().getId());
    }

    @Override
    public void removeQuestion(Long id) throws RahmaniaException {
        Question question = getQuestion(id);
        questionRepository.delete(question);
    }

    @Override
    public void updateFillGapQuestion(FillGapQuestionDTO questionDTO, Long id) throws Exception {
        Question savedQuestion = getQuestion(questionDTO.getId());
        saveFillGapQuestion(questionDTO, savedQuestion.getSubject().getId());
    }

    @Override
    public List<? extends QuestionDTO> listStudetnQuestions() throws RahmaniaException, IOException {

        Student currentStudent = (Student) userRepository.findByMobileNumber(SecurityHelper.getCurrentUser());
        // took the exam
        if (IsTheStudentTookTheExam(currentStudent))
            return null;

        // have saved answer
        if (IsTheStudetHasSaveAnswers(currentStudent)) {
            ObjectMapper mapper = new ObjectMapper();
            List<StudentAnswersDTO> answersDTOS = mapper.readValue(currentStudent.getStudentSavedAnswers(), new TypeReference<ArrayList<StudentAnswersDTO>>() {});
           return  answersDTOS;
        }

        // first time
        List<QuestionDTO> result = new ArrayList<>();
        List<Question> questions = questionRepository.getAllQuestions();
        questions.forEach(e -> {
            result.add(SimpleQuestionFactoryTransformer.convertQuestionToStudentModel(e, transformer));
        });
        return result;
    }

    @Override
    public void saveWhatQuestion(WhatQuestionDTO questionDTO, Long tabId) {
        Subject subject = getSubject(tabId);
        WhatQuestion question = transformer.transform(questionDTO, WhatQuestion.class);
        question.setSubject(subject);
        questionRepository.save(question);
    }

    @Override
    public void updateWhatQuestion(WhatQuestionDTO questionDTO, Long id) {
        Question savedQuestion = getQuestion(questionDTO.getId());
        saveWhatQuestion(questionDTO, savedQuestion.getSubject().getId());
    }

    private boolean IsTheStudentTookTheExam(Student currentStudent) {
        if (Objects.isNull(currentStudent.getGrade()))
            return false;

        return true;
    }

    private boolean IsTheStudetHasSaveAnswers(Student currentStudent) {
        if (Objects.isNull(currentStudent.getStudentSavedAnswers()))
            return false;

        return true;
    }
    @Override
    public void endTest() {
        menuReposiroty.disableTestMenu();
    }

}
