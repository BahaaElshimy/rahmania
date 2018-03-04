package com.rahmania.dto.question;

import java.util.ArrayList;
import java.util.List;

public class FillGapQuestionDTO extends QuestionDTO {

    private List<String> parts = new ArrayList<>();

    public List<String> getParts() {
        return parts;
    }

    public void setParts(List<String> parts) {
        this.parts = parts;
    }
}
