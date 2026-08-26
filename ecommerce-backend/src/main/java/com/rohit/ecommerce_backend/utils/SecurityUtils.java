package com.rohit.ecommerce_backend.utils;

import com.rohit.ecommerce_backend.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    public User getCurrentUser(){

        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        if(authentication == null || !authentication.isAuthenticated()){
            throw new RuntimeException("User is not authenticated");
        }

        return (User) authentication.getPrincipal();
    }

}
