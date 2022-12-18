package ru.job4j.urlshortcut.service;

import ru.job4j.urlshortcut.model.Site;

import java.util.Optional;

public interface SiteService {

    Optional<Site> findByLogin(String login);

    Optional<Site> findByName(String name);

    Site save(Site site, int length);
}
