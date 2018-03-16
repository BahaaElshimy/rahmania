package com.rahmania.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by bahaa on 07/02/18.
 */

public class SecurityHelper {

    public static String getCurrentUser(){
       return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    public static boolean isAuthenticatedUser() {
        return !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }

}
