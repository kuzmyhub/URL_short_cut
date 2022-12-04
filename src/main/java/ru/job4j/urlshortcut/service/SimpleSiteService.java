package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.repository.SiteRepository;

@Service
@AllArgsConstructor
public class SimpleSiteService implements SiteService {

    private SiteRepository siteRepository;

    public Site findByLogin(String login) {
        return siteRepository.findByLogin(login);
    }

    public Site save(Site site) {
        return siteRepository.save(site);
    }

}
