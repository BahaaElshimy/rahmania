package com.rahmania.service.question;

import com.rahmania.dto.question.WhatQuestionDTO;
import com.rahmania.exception.RahmaniaException;
import com.rahmania.dto.question.FillGapQuestionDTO;
import com.rahmania.dto.question.MultiChoiceQuestionDTO;
import com.rahmania.dto.question.QuestionDTO;

import java.io.IOException;
import java.util.List;

public interface QuestionService {

    QuestionDTO getQuestionDTO(long id);

    List<QuestionDTO> listAll();

    void saveMultiChoiceQuestion(MultiChoiceQuestionDTO questionDTO, Long tabId) throws Exception;

    void saveFillGapQuestion(FillGapQuestionDTO questionDTO, Long tabId) throws Exception;

    void updateMultiChoiceQuestion(MultiChoiceQuestionDTO questionDTO ,Long id) throws Exception;

    void removeQuestion(Long id);

    void updateFillGapQuestion(FillGapQuestionDTO questionDTO , Long id) throws Exception;

    List<? extends QuestionDTO> listStudetnQuestions() throws RahmaniaException, IOException;

    void saveWhatQuestion(WhatQuestionDTO questionDTO, Long tabId);

    void updateWhatQuestion(WhatQuestionDTO questionDTO, Long id);

    void endTest();
}
