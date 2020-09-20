package com.fact.util;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

	public static final String ROLE_ORDONNATEUR = "ROLE_ORDONNATEUR";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";
	
    public static String getCurrentUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {

            return ((UserDetails) principal).getUsername();

        } else {

            return principal.toString();
        }
    }

    public static String getCurrentUtilisateur() {
        return getCurrentUserEmail();
    }
    
    public static Set<String> getCurrentUserRoles() {
    	Set<String> roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
    		     .map(r -> r.getAuthority()).collect(Collectors.toSet());
        return roles;
    }
}


