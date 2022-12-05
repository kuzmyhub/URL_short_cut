package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Site;

import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
@AllArgsConstructor
public class SiteDetailsServiceImpl implements UserDetailsService {
    private SimpleSiteService siteService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Site> optionalUser = siteService.findByLogin(login);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(login);
        }
        Site user = optionalUser.get();
        return new User(user.getLogin(), user.getPassword(), emptyList());
    }
}
