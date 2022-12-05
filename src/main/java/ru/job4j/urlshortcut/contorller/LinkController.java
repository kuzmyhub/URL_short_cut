package ru.job4j.urlshortcut.contorller;

import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.urlshortcut.model.DTOLink;
import ru.job4j.urlshortcut.model.Link;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.service.LinkService;
import ru.job4j.urlshortcut.service.SiteService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/link")
@AllArgsConstructor
public class LinkController {

    private LinkService simpleLinkService;
    private SiteService simpleSiteService;

    @PostMapping("/convert")
    public Map<String, String> convert(@RequestBody Link link) {
        String siteLogin = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal().toString();
        link.setCode(RandomString.make(6));
        link.setSite(simpleSiteService.
                findByLogin(siteLogin)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format(
                                        "Site with login %s not found",
                                        siteLogin
                                )
                        )
                )
        );
        simpleLinkService.save(link);
        return Map.of(
                "code", link.getCode()
        );
    }

    @GetMapping("/redirect/{code}")
    public ResponseEntity<String> redirect(@PathVariable String code) {
        Link link = simpleLinkService
                .findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Code %s has no associations", code)));
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .header(code, link.getUrl())
                .build();
    }

    @GetMapping("/statistic")
    public Map<String, String> statistic() {
        String siteLogin = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal().toString();
        Site site = simpleSiteService.
                findByLogin(siteLogin)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format(
                                        "Site with login %s not found",
                                        siteLogin
                                )
                        )
                );
        List<Link> links = simpleLinkService.findAllBySite(site);
        List<DTOLink> DTOLinks = links
                .stream()
                .map(x -> new DTOLink(x.getUrl(), x.getTotal()))
                .toList();
        return new HashMap<>();
    }
}
