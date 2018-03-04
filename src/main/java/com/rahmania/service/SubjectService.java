package com.rahmania.service;

import com.rahmania.dto.ExamSubjectDTO;
import com.rahmania.dto.SubjectDTO;
import com.rahmania.dto.question.QuestionDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SubjectService {

    SubjectDTO getSubject(long id);

    List<SubjectDTO> listAll();

    void saveSubject(SubjectDTO subjectDTO, MultipartFile file, HttpServletRequest request)throws Exception ;
    void updateSubject(SubjectDTO subjectDTO, MultipartFile file, HttpServletRequest request, Long id)throws Exception ;

    void deleteSubject(Long id);

    List<QuestionDTO> retrieveSubjectQuestions(Long id);

    List<ExamSubjectDTO> loadExam();

    void removeSubjectVideo(Long subjectId);

}
