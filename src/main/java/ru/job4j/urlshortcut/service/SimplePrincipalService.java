package ru.job4j.urlshortcut.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SimplePrincipalService implements PrincipalService {

    public String getSiteLogin() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal().toString();
    }
}
