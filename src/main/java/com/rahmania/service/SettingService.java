package com.rahmania.service;

import com.rahmania.model.AboutDTO;
import com.rahmania.model.ConstraintDTO;
import com.rahmania.model.PrizeDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by bahaa on 10/02/18.
 */

public interface SettingService {

    void addPrize(PrizeDTO prize) throws Exception;

    void editPrize(PrizeDTO prizeDTO, Long id) throws Exception;

    void deletePrize(Long id) throws Exception;

    void AddAbout(AboutDTO aboutDTO) throws Exception;

    void editAbout(AboutDTO aboutDTO, Long id) throws Exception;


    void addConstraing(ConstraintDTO constraint) throws Exception;

    void editConstraing(ConstraintDTO constraint, Long id) throws Exception;

    void deleteConstraing(Long id) throws Exception;

    AboutDTO getAbout();

    List<PrizeDTO> retrievePrizes();

    List<ConstraintDTO> retrieveConstraints();

    void uploadPrizesImage(MultipartFile file, HttpServletRequest request) throws Exception;


    String getImage(HttpServletRequest request);

    void deletePrizeImage(HttpServletRequest request);
}
