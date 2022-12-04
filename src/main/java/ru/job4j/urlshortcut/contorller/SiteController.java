package ru.job4j.urlshortcut.contorller;

import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.service.SimpleSiteService;

@RestController
@RequestMapping("/site")
@AllArgsConstructor
public class SiteController {

    private SimpleSiteService siteService;
    private BCryptPasswordEncoder encoder;

    @PostMapping("/sign-up")
    public Site signUp(@RequestBody Site site) {
        site.setLogin(RandomString.make(6));
        site.setPassword(RandomString.make(6));
        site.setRegistration(true);
        Site encodeSite = new Site();
        encodeSite.setName(site.getName());
        encodeSite.setLogin(site.getLogin());
        encodeSite.setPassword(encoder.encode(site.getPassword()));
        encodeSite.setRegistration(site.isRegistration());
        siteService.save(encodeSite);
        site.setId(encodeSite.getId());
        return site;
    }
}
