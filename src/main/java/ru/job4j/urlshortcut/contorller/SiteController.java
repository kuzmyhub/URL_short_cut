package ru.job4j.urlshortcut.contorller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.service.SiteService;

import javax.validation.Valid;
import java.util.Map;

@RestController
@PropertySource("classpath:application.properties")
@RequestMapping("/site")
@AllArgsConstructor
public class SiteController {

    private SiteService simpleSiteService;

    @PostMapping("/sign-up")
    public Map<String, String> signUp(@Valid @RequestBody Site site,
                                      @Value("${NUMBER_OF_SYMBOLS_OF_UNIQUE_CODE}") int length) {
        Site registeredSite = simpleSiteService.save(site, length);
        return Map.of(
                "registration", String.valueOf(registeredSite.isRegistration()),
                "login", registeredSite.getLogin(),
                "password", registeredSite.getPassword());
    }
}
