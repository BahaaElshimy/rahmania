package com.rahmania.repository;

import com.rahmania.dto.QuestionRightAnswerDTO;
import com.rahmania.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findBySubjectId(Long id);

    @Query("select new com.rahmania.dto.QuestionRightAnswerDTO(a.question.id , a.id)from MultiChoiceAnswer a where a.correct = true " )
    List<QuestionRightAnswerDTO> getRightAnswers();


    @Query("select q from Question  q   order by q.subject.id" )
    List<Question> getAllQuestions();
}
