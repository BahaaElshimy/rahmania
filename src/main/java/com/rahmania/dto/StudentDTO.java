package com.rahmania.dto;

/**
 * Created by bahaa on 07/02/18.
 */
public class StudentDTO extends AbstractDTO {

    private String name;
    private Long grade;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }
}
