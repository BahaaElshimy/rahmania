package com.rahmania.service;

import com.rahmania.dto.MenueDTO;
import com.rahmania.entity.Menue;
import com.rahmania.entity.Student;
import com.rahmania.entity.User;
import com.rahmania.repository.RoleRepository;
import com.rahmania.security.SecurityHelper;
import com.rahmania.service.user.UserService;
import com.rahmania.util.AppConstants;
import com.rahmania.util.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<MenueDTO> loadUserMenue() {
        User user = getUser();
        String role = Objects.nonNull(user) ? user.getRole().getName() : AppConstants.AnonymousRole;
        List<Menue> userMenus = roleRepository.loadUserMenue(role);
        prepareMenueForUser(user, userMenus);
        return transformer.transform(userMenus, MenueDTO.class);

    }

    private void prepareMenueForUser(final User user, final List<Menue> userMenus) {
        boolean tookExam = Objects.nonNull(user) && user instanceof Student && Objects.nonNull(((Student) user).getGrade()) ? true : false;
        userMenus.removeIf(m -> (tookExam && m.getUrl().equals("/test")) || m.isDisabled());
    }

    // TODO enhance this method to get user form session
    private User getUser() {
        if (SecurityHelper.isAuthenticatedUser()) {
            return userService.getUSer(SecurityContextHolder.getContext().getAuthentication().getName());
        }
        return null;
    }



}
