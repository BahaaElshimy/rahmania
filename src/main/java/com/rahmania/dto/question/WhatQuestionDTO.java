package com.rahmania.dto.question;

/**
 * Created by bahaa on 27/02/18.
 */
public class WhatQuestionDTO extends QuestionDTO{

    private String answer;

    public WhatQuestionDTO(){

    }
    public WhatQuestionDTO(String answer){

        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
