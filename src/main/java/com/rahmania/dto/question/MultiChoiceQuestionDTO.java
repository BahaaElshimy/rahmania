package com.rahmania.dto.question;

import java.util.List;

public class MultiChoiceQuestionDTO extends QuestionDTO {


    private List<MultiChoiceAnswerDTO> answers;


    public List<MultiChoiceAnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<MultiChoiceAnswerDTO> answers) {
        this.answers = answers;
    }
}
