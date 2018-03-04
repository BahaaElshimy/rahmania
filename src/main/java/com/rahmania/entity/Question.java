package com.rahmania.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public  class Question extends AbstractEntity {

    private String title;

    private String question;

    @ManyToOne
    Subject subject;

    private String videoPath;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }


    @OneToMany( mappedBy = "question",cascade = CascadeType.ALL)
    private List<MultiChoiceAnswer> answers;

    public List<MultiChoiceAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<MultiChoiceAnswer> answers) {
        this.answers = answers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
}
