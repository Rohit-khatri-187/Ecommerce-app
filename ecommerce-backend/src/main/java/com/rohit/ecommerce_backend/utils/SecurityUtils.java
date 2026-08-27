package com.rohit.ecommerce_backend.utils;

import com.rohit.ecommerce_backend.entities.User;
import com.rohit.ecommerce_backend.security.CustomUserDetail;
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

        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();

        return customUserDetail.getUser();
    }

}
