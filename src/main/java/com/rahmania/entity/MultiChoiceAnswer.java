package com.rahmania.entity;



import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class MultiChoiceAnswer extends Answer {

    private String answer;

    private Boolean correct;

    @ManyToOne
    private Question question;

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
