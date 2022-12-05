package ru.job4j.urlshortcut.contorller;

import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.service.SiteService;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/site")
@AllArgsConstructor
public class SiteController {

    private SiteService simpleSiteService;
    private BCryptPasswordEncoder encoder;

    @PostMapping("/sign-up")
    public Map<String, String> signUp(@Valid @RequestBody Site site) {
        Optional<Site> optionalSite = simpleSiteService
                .findByName(site.getName());
        if (optionalSite.isPresent()) {
            return Map.of(
                    "registration", String.valueOf(!site.isRegistration()),
                    "login", optionalSite.get().getLogin(),
                    "password", (optionalSite.get().getPassword()));
        }
        String login = RandomString.make(6);
        String password = RandomString.make(6);
        site.setLogin(login);
        site.setPassword(encoder.encode(password));
        simpleSiteService.save(site);
        return Map.of(
                "registration", String.valueOf(site.isRegistration()),
                "login", login,
                "password", password);
    }
}
