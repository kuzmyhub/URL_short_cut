package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.urlshortcut.model.Link;
import ru.job4j.urlshortcut.model.Site;

import java.util.List;
import java.util.Optional;

@Repository
public interface SiteRepository extends CrudRepository<Site, Integer> {

    Optional<Site> findByLogin(String login);

    Optional<Site> findByName(String name);
}
