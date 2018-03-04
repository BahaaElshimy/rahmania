package com.rahmania.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class FillGapAnswer extends Answer {

    @OneToOne
    private Question question;

    private String commaSepratedAnswer;

    public String getCommaSepratedAnswer() {
        return commaSepratedAnswer;
    }

    public void setCommaSepratedAnswer(String commaSepratedAnswer) {
        this.commaSepratedAnswer = commaSepratedAnswer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
