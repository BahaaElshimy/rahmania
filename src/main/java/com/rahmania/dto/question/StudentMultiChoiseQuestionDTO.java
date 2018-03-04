package com.rahmania.dto.question;

import java.util.List;

/**
 * Created by bahaa on 04/02/18.
 */
public class StudentMultiChoiseQuestionDTO extends QuestionDTO {

    private List<StudentMultiChoiceAnswerDTO> answers;

    public List<StudentMultiChoiceAnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<StudentMultiChoiceAnswerDTO> answers) {
        this.answers = answers;
    }
}
