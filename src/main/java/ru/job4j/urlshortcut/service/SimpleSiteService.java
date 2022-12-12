package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.repository.SiteRepository;

import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleSiteService implements SiteService {

    static final int NUMBER_OF_SYMBOLS_OF_UNIQUE_CODE = 6;

    private SiteRepository siteRepository;
    private BCryptPasswordEncoder encoder;

    public Optional<Site> findByLogin(String login) {
        return siteRepository.findByLogin(login);
    }

    public Optional<Site> findByName(String name) {
        return siteRepository.findByName(name);
    }

    public Site save(Site site) {
        Optional<Site> optionalSite = findByName(site.getName());
        if (optionalSite.isPresent()) {
            optionalSite.get().setRegistration(false);
            return optionalSite.get();
        }
        site.setLogin(generateUniqueCode(NUMBER_OF_SYMBOLS_OF_UNIQUE_CODE));
        String password = generateUniqueCode(NUMBER_OF_SYMBOLS_OF_UNIQUE_CODE);
        site.setPassword(encoder.encode(password));
        siteRepository.save(site);
        site.setPassword(password);
        return site;
    }

    public String generateUniqueCode(int numberOfCharacters) {
        return RandomString.make(numberOfCharacters);
    }
}
