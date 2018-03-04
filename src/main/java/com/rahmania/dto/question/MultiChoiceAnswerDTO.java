package com.rahmania.dto.question;

import com.rahmania.dto.AbstractDTO;

public class MultiChoiceAnswerDTO extends AbstractDTO {

    private String answer;

    private Boolean correct;

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


}
