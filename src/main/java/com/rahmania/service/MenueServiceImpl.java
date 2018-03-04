package com.rahmania.service;

import com.rahmania.dto.MenueDTO;
import com.rahmania.dto.user.UserDTO;
import com.rahmania.entity.Menue;
import com.rahmania.entity.Student;
import com.rahmania.entity.Users;
import com.rahmania.repository.RoleRepository;
import com.rahmania.service.user.UserService;
import com.rahmania.util.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by bahaa on 30/01/18.
 */

@Service
public class MenueServiceImpl implements MenueService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    Transformer transformer;

    @Autowired
    UserService userService;

    // TODO enahnce this method to get user form session
    @Override
    public List<MenueDTO> loadUserMenue() {
          String role  = "anonymous";
          Users user = null;
        if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)  ) {
             user = userService.getUSer(SecurityContextHolder.getContext().getAuthentication().getName());
            role = user.getRole().getName();
        }
        List<Menue> userMenus = roleRepository.loadUserMenue(role);
        boolean tookExam = Objects.nonNull(user) && user instanceof Student && Objects.nonNull(((Student)user).getGrade()) ? true :  false;
        userMenus.removeIf(m -> (tookExam && m.getUrl().equals("/test")));
        userMenus.removeIf(m -> m.isDisabled());
        return transformer.transform(userMenus, MenueDTO.class);

    }

}
