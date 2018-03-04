package com.rahmania.dto;

import com.rahmania.dto.question.QuestionDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bahaa on 05/02/18.
 */
public class ExamSubjectDTO {

    public ExamSubjectDTO(){

    }

    public ExamSubjectDTO(String name  ,List<QuestionDTO> questions){
        this.name = name;
        this.questionList = questions;
    }

    private String name;

    private List<QuestionDTO> questionList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QuestionDTO> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionDTO> questionList) {
        this.questionList = questionList;
    }
}
