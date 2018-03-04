package com.rahmania.service.question;

import com.rahmania.dto.question.*;
import com.rahmania.entity.FillGapQuestion;
import com.rahmania.entity.MultiChoiceQuestion;
import com.rahmania.entity.Question;
import com.rahmania.util.Transformer;

public class SimpleQuestionFactoryTransformer {

    //TODO How to solve this using polymerphism
    public static QuestionDTO convertQuestionToModel(Question question, Transformer transformer) {

        if (question instanceof MultiChoiceQuestion)
            return transformer.transform(question, MultiChoiceQuestionDTO.class);
        else if (question instanceof FillGapQuestion) {
            return transformer.transform(question, FillGapQuestionDTO.class);
        } else {
            return transformer.transform(question, WhatQuestionDTO.class);
        }
    }

    public static QuestionDTO convertQuestionToStudentModel(Question question, Transformer transformer) {

        if (question instanceof MultiChoiceQuestion)
            return transformer.transform(question, StudentMultiChoiseQuestionDTO.class);
        else if (question instanceof FillGapQuestion) {
            return transformer.transform(question, FillGapQuestionDTO.class);
        } else {
            return transformer.transform(question, WhatQuestionDTO.class);
        }
    }

}
