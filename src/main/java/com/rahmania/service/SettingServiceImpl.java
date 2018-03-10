package com.rahmania.service;

import com.rahmania.dto.user.ChangePasswordDTO;
import com.rahmania.entity.About;
import com.rahmania.entity.Rule;
import com.rahmania.entity.Prize;
import com.rahmania.entity.Users;
import com.rahmania.exception.FieldErrorDTO;
import com.rahmania.exception.RahmaniaException;
import com.rahmania.model.AboutDTO;
import com.rahmania.model.ConstraintDTO;
import com.rahmania.model.PrizeDTO;
import com.rahmania.repository.AboutRepository;
import com.rahmania.repository.ConstraintsRepository;
import com.rahmania.repository.PrizesRepository;
import com.rahmania.repository.UserRepository;
import com.rahmania.security.SecurityHelper;
import com.rahmania.util.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

/**
 * Created by bahaa on 10/02/18.
 */
@Service
@Transactional
public class SettingServiceImpl implements SettingService {


    @Autowired
    PrizesRepository prizesRepository;

    @Autowired
    AboutRepository aboutRepository;

    @Autowired
    ConstraintsRepository constraintsRepository;

    @Autowired
    Transformer transformer;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;




    @Override
    public void addPrize(PrizeDTO prize) throws Exception {
        prizesRepository.save(transformer.transform(prize, Prize.class));
    }

    @Override
    public void editPrize(PrizeDTO prizeDTO, Long id) throws Exception {
        Prize prize = prizesRepository.findOne(id);
        Objects.requireNonNull(prize);
        addPrize(prizeDTO);
    }

    @Override
    public void deletePrize(Long id) throws Exception {
        Prize prize = prizesRepository.findOne(id);
        Objects.requireNonNull(prize);
        prizesRepository.delete(prize);
    }


    @Override
    public void AddAbout(AboutDTO aboutDTO) throws Exception {
        aboutRepository.save(transformer.transform(aboutDTO, About.class));
    }

    @Override
    public void editAbout(AboutDTO aboutDTO, Long id) throws Exception {
        About about = aboutRepository.findOne(id);
        Objects.requireNonNull(about);
        AddAbout(aboutDTO);
    }

    @Override
    public void addConstraing(ConstraintDTO constraint) throws Exception {
        constraintsRepository.save(transformer.transform(constraint, Rule.class));
    }

    @Override
    public void editConstraing(ConstraintDTO constraint, Long id) throws Exception {
        Rule rule1 = constraintsRepository.findOne(id);
        Objects.requireNonNull(rule1);
        addConstraing(constraint);
    }

    @Override
    public void deleteConstraing(Long id) throws Exception {
        Rule rule1 = constraintsRepository.findOne(id);
        Objects.requireNonNull(rule1);
        constraintsRepository.delete(rule1);
    }

    @Override
    public AboutDTO getAbout() {
        List<About> aboutDTOS = aboutRepository.findAll();
        if (!aboutDTOS.isEmpty())
            return transformer.transform(aboutRepository.findAll().get(0), AboutDTO.class);
        else
            return null;
    }

    @Override
    public List<PrizeDTO> retrievePrizes() {
        return transformer.transform(prizesRepository.findAll(), PrizeDTO.class);
    }

    @Override
    public List<ConstraintDTO> retrieveConstraints() {
        return transformer.transform(constraintsRepository.findAll(), ConstraintDTO.class);
    }

    @Override
    public void uploadPrizesImage(MultipartFile file, HttpServletRequest request) throws Exception {

        if (file.isEmpty())
            return;
        try {
            byte[] bytes = file.getBytes();
            String realPath = request.getServletContext().getRealPath("/resources/image");

            File dir = new File(realPath);
            if (dir.exists()) {
                FileSystemUtils.deleteRecursively(dir);
            }

            dir.mkdir();

            Path path = Paths.get(dir + "/" + file.getOriginalFilename());
            Files.write(path, bytes);
            FileWriter fileWriter = new FileWriter(new File(dir + "/fileName.txt"));
            fileWriter.write(file.getOriginalFilename());
            fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("FAIL!");
        }

    }

    @Override
    public String getImage(HttpServletRequest request) {
        String s = "";
        String realPath = request.getServletContext().getRealPath("/resources/image/fileName.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(realPath)))) {
            s = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public void deletePrizeImage(HttpServletRequest request) {
        String realPath = request.getServletContext().getRealPath("/resources/image");
        File dir = new File(realPath);
        if (dir.exists()) {
            FileSystemUtils.deleteRecursively(dir);
        }
    }

    @Override
    public FieldErrorDTO changePassword(ChangePasswordDTO changePasswordDTO) {
        Users user = userRepository.findByMobileNumber(SecurityHelper.getCurrentUser());


        if (bCryptPasswordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())){
            user.setPassword(bCryptPasswordEncoder.encode(changePasswordDTO.getNewPassword()));
            return  new FieldErrorDTO();
        }else{
            return new FieldErrorDTO("كلمة المرور الحالية غير صحيحة", "كلمة المرور الحالية غير صحيحة");
        }
    }
}
