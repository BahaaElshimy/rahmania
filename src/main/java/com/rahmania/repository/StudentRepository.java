package com.rahmania.repository;

import com.rahmania.dto.SimpleStudentAnswerDTO;
import com.rahmania.dto.StudentDTO;
import com.rahmania.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by bahaa on 07/02/18.
 */
public interface StudentRepository extends JpaRepository<Student ,Long> {

    @Modifying(clearAutomatically = true)
    @Query("update Student s set s.elimnated =true , s.participated =false where s.id in(:ids)")
    void eleminateStudnts(@Param("ids") List<Long> studentIds);

    @Modifying(clearAutomatically = true)
    @Query("update Student s set s.participated =true ,s.elimnated =false , s.candidate = false where s.id in(:ids)")
    void moveToParticipate(@Param("ids")List<Long> ids);

    @Modifying(clearAutomatically = true)
    @Query("update Student s set s.participated =true ,s.elimnated =false ,s.candidate = false  ")
    void moveToAllStudentsTParticipate();

    List<Student> findByParticipatedIsTrue();

    List<Student> findByElimnatedIsFalse();
    @Modifying(clearAutomatically = true)
    @Query("update Student s set s.elimnated =false , s.participated =false  ,s.candidate = true where s.id in(:ids)")
    void moveToCandidate(@Param("ids") List<Long> studentIs);

    List<Student> findByCandidateIsTrue();
    //List<SimpleStudentAnswerDTO> retrieveStudentAnswers(@Param("studentId") Long studentId);
}
