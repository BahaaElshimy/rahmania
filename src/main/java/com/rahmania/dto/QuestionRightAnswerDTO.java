package com.rahmania.dto;

/**
 * Created by bahaa on 07/02/18.
 */
public class QuestionRightAnswerDTO {

    public QuestionRightAnswerDTO() {

    }

    public QuestionRightAnswerDTO(Long questionId, Long answerId) {
        this.questionId = questionId;
        this.answerId = answerId;
    }

    private Long questionId;
    private Long answerId;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }
}
