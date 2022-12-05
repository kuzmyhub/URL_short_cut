package ru.job4j.urlshortcut.util;

import org.springframework.security.core.context.SecurityContextHolder;

public final class SiteLogin {

    public static String getSiteLogin() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal().toString();
    }
}
