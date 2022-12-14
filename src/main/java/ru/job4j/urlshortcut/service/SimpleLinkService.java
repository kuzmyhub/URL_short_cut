package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Link;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.repository.LinkRepository;
import ru.job4j.urlshortcut.util.SiteLogin;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleLinkService implements LinkService {

    static final int NUMBER_OF_SYMBOLS_OF_SHORT_LINK = 6;

    private LinkRepository linkRepository;
    private SiteService simpleSiteService;

    public List<Link> findAllBySite() {
        Site site = simpleSiteService.
                findByLogin(SiteLogin.getSiteLogin())
                .orElseThrow(() -> new NoSuchElementException(
                        "Invalid login"
                ));
        return linkRepository.findAllBySite(site);
    }

    public Link save(Link link) {
        Optional<Link> optionalLink = findByUrl(link.getUrl());
        if (optionalLink.isPresent()) {
            return optionalLink.get();
        }
        Site site = simpleSiteService
                .findByLogin(SiteLogin.getSiteLogin())
                .orElseThrow(() -> new NoSuchElementException(
                        "Invalid login"
                ));
        link.setSite(site);
        link.setCode(generateShortLink(NUMBER_OF_SYMBOLS_OF_SHORT_LINK));
        return linkRepository.save(link);
    }

    public Optional<Link> findByCode(String code) {
        return linkRepository.findByCode(code);
    }

    public void updateTotal(String code) {
        linkRepository.updateTotal(code);
    }

    public String generateShortLink(int numberOfCharacters) {
        return RandomString.make(numberOfCharacters);
    }

    public Optional<Link> findByUrl(String url) {
        return linkRepository.findByUrl(url);
    }
}
