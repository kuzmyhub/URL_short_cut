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

    @PostMapping("/sign-up")
    public Map<String, String> signUp(@Valid @RequestBody Site site) {
        Site registeredSite = simpleSiteService.save(site);
        return Map.of(
                "registration", String.valueOf(registeredSite.isRegistration()),
                "login", registeredSite.getLogin(),
                "password", registeredSite.getPassword());
    }
}
