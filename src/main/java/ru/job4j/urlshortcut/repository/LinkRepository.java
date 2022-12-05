package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.urlshortcut.model.Link;
import ru.job4j.urlshortcut.model.Site;

import java.util.List;
import java.util.Optional;

@Repository
public interface LinkRepository extends CrudRepository<Link, Integer> {

    List<Link> findAllBySite(Site site);

    Optional<Link> findByCode(String code);
}
