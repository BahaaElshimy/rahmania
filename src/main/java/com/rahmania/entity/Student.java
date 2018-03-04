package com.rahmania.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Student extends Users {

    private Long grade;

    private boolean elimnated;

    private boolean participated;

    private boolean candidate;

    @Column(columnDefinition = "TEXT")
    private String studentSavedAnswers;

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }

    public boolean isElimnated() {
        return elimnated;
    }

    public void setElimnated(boolean elimnated) {
        this.elimnated = elimnated;
    }

    public boolean isParticipated() {
        return participated;
    }

    public void setParticipated(boolean participated) {
        this.participated = participated;
    }

    public String getStudentSavedAnswers() {
        return studentSavedAnswers;
    }

    public void setStudentSavedAnswers(String studentSavedAnswers) {
        this.studentSavedAnswers = studentSavedAnswers;
    }

    public boolean isCandidate() {
        return candidate;
    }

    public void setCandidate(boolean candidate) {
        this.candidate = candidate;
    }
}
