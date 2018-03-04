package com.rahmania.service;

import com.rahmania.entity.Question;
import com.rahmania.entity.Subject;
import com.rahmania.exception.RahmaniaException;
import com.rahmania.dto.ExamSubjectDTO;
import com.rahmania.dto.SubjectDTO;
import com.rahmania.dto.question.QuestionDTO;
import com.rahmania.repository.QuestionRepository;
import com.rahmania.repository.SubjectRepository;
import com.rahmania.service.question.SimpleQuestionFactoryTransformer;
import com.rahmania.util.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Transformer transformer;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    StorageService storageService;

    @Override
    public SubjectDTO getSubject(long id) {
        return transformer.transform(subjectRepository.findOne(id), SubjectDTO.class);
    }

    @Override
    public List<SubjectDTO> listAll() {
        List<Subject> subjects = subjectRepository.findAll();
        return transformer.transform(subjects, SubjectDTO.class);
    }

    @Override
    public void saveSubject(SubjectDTO subjectDTO, MultipartFile file, HttpServletRequest request) throws Exception{
        Subject  subject =   subjectRepository.save(transformer.transform(subjectDTO, Subject.class));
        prepareSubjectVideo(file, subject,storageService.store(file, subject.getId(), request));
    }

    private void prepareSubjectVideo(MultipartFile file, Subject subject, String store) {
        subject.setVideoPath(store);
        subject.setVideoName(file.getOriginalFilename());
    }

    @Override
    public void updateSubject(SubjectDTO subjectDTO, MultipartFile file, HttpServletRequest request, Long id) throws Exception {
        Subject saveSubject =   subjectRepository.findOne(id);
        Objects.requireNonNull(saveSubject);
        Subject  subject =   subjectRepository.save(transformer.transform(subjectDTO, Subject.class));
        if(file!=null) {
            prepareSubjectVideo(file, subject, storageService.store(file, id, request));
        }
    }

    @Override
    public void deleteSubject(Long id) throws RahmaniaException {
        Subject subject = subjectRepository.findOne(id);
        if (Objects.isNull(subject))
            throw new RahmaniaException("wrong id", "Entity not found");
        subjectRepository.delete(subject);

    }

    @Override
    public List<QuestionDTO> retrieveSubjectQuestions(Long id) {
        List<QuestionDTO> result = new ArrayList<>();
        List<Question> questions = questionRepository.findBySubjectId(id);
        questions.forEach(e -> {
            result.add(SimpleQuestionFactoryTransformer.convertQuestionToModel(e, transformer));
        });
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExamSubjectDTO> loadExam() {
        List<Subject> subjects = subjectRepository.findAll();
       List<ExamSubjectDTO> examSubjectDTOS = new ArrayList<>();

       for (Subject subject : subjects){
           examSubjectDTOS.add(new ExamSubjectDTO(subject.getName()  ,getQuestions(subject.getQuestionList()) ));
       }
     return  examSubjectDTOS;
    }

    @Override
    public void removeSubjectVideo(Long subjectId) {
        SubjectDTO subject = getSubject(subjectId);
        storageService.deleteFile(subject.getVideoPath());
    }

    private List<QuestionDTO> getQuestions(List<Question> questions) {
        List<QuestionDTO> result = new ArrayList<>();
        questions.forEach(e -> {
            result.add(SimpleQuestionFactoryTransformer.convertQuestionToModel(e, transformer));
        });
        return result;
    }
}
