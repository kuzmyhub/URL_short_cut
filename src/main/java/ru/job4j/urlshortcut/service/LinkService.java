package ru.job4j.urlshortcut.service;

import ru.job4j.urlshortcut.model.Link;
import ru.job4j.urlshortcut.model.Site;

import java.util.List;
import java.util.Optional;

public interface LinkService {

    List<Link> findAllBySite(String siteLogin);

    Link save(Link link, String siteLogin, int length);

    Optional<Link> findByCode(String code);

    void updateTotal(String code);
}
