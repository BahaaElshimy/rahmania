package com.rahmania.service.user;

import com.rahmania.entity.Role;
import com.rahmania.entity.Student;
import com.rahmania.entity.User;
import com.rahmania.dto.user.UserDTO;
import com.rahmania.dto.user.UserRegisterationDTO;
import com.rahmania.repository.RoleRepository;
import com.rahmania.repository.StudentRepository;
import com.rahmania.repository.UserRepository;
import com.rahmania.sms.SMSSender;
import com.rahmania.util.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Transformer transformer;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    SMSSender smsSender;

    @Override
    @Transactional
    public UserDTO saveUser(UserRegisterationDTO userDTO) throws UnsupportedEncodingException {
        Student user = transformer.transform(userDTO, Student.class);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByName("student");
        user.setRole(role);
        user.setToken(generateToken());

       UserDTO saveUserDTO =  transformer.transform(studentRepository.save(transformer.transform(user, Student.class)), UserDTO.class);
        smsSender.sendRegisterationMessage(user.getMobileNumber() , user.getToken() + "   كود التأكيد");
        return  saveUserDTO;
    }

    @Override
    public List<UserDTO> listUsers() {
        return transformer.transform(userRepository.findAll(), UserDTO.class);
    }

    @Override
    public UserDTO retriveUser(Long id) {
        return transformer.transform(userRepository.findOne(id), UserDTO.class);
    }

    //TODO check how to set mapper.xml
    @Override
    public UserDTO retrieveUserByMobile(String mobile) {
        User user = userRepository.findByMobileNumber(mobile);
        UserDTO userDTO = transformer.transform(user, UserDTO.class);
        userDTO.setRole(user.getRole().getName());
        return userDTO;
    }

    @Override
    public User getUSer(String mobile) {
        return userRepository.findByMobileNumber(mobile);
    }

    private String generateToken() {
        Random random = new Random();
        return String.format("%05d", random.nextInt(100000));
    }


}
