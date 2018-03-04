package com.rahmania.dto;

import com.rahmania.dto.question.AnswerDTO;
import com.rahmania.dto.question.QuestionDTO;
import com.rahmania.entity.MultiChoiceAnswer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bahaa on 07/02/18.
 */
public class StudentAnswersDTO extends QuestionDTO {

    private Long answerId;
    private List<String> parts = new ArrayList<>();
    private  List<MultiChoiceAnswer> answers  = new ArrayList<>();
    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public List<String> getParts() {
        return parts;
    }

    public void setParts(List<String> parts) {
        this.parts = parts;
    }

    public List<MultiChoiceAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<MultiChoiceAnswer> answers) {
        this.answers = answers;
    }
}
