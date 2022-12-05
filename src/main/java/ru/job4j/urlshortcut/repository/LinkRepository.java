package ru.job4j.urlshortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.urlshortcut.model.Link;
import ru.job4j.urlshortcut.model.Site;

import java.util.List;
import java.util.Optional;

@Repository
public interface LinkRepository extends CrudRepository<Link, Integer> {

    String UPDATE_TOTAL = "UPDATE Link l SET l.total = total + 1 WHERE l.code = :fCode";

    List<Link> findAllBySite(Site site);

    Optional<Link> findByCode(String code);

    @Transactional
    @Modifying
    @Query(UPDATE_TOTAL)
    void updateTotal(@Param("fCode") String code);
}
