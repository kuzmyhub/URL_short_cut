package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Link;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.repository.LinkRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleLinkService implements LinkService {

    private LinkRepository linkRepository;

    public List<Link> findAllBySite(Site site) {
        return linkRepository.findAllBySite(site);
    }

    public Link save(Link link) {
        return linkRepository.save(link);
    }

    public Optional<Link> findByCode(String code) {
        return linkRepository.findByCode(code);
    }
}
