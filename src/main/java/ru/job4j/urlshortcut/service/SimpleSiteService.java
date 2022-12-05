package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.repository.SiteRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleSiteService implements SiteService {

    private SiteRepository siteRepository;

    public Optional<Site> findByLogin(String login) {
        return siteRepository.findByLogin(login);
    }

    public Optional<Site> findByName(String name) {
        return siteRepository.findByName(name);
    }

    public Site save(Site site) {
        return siteRepository.save(site);
    }

}
