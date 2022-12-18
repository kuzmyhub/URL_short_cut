package ru.job4j.urlshortcut.contorller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.urlshortcut.model.Link;
import ru.job4j.urlshortcut.service.LinkService;
import ru.job4j.urlshortcut.util.SiteLogin;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@PropertySource("classpath:application.properties")
@RequestMapping("/link")
@AllArgsConstructor
public class LinkController {

    private LinkService simpleLinkService;

    @PostMapping("/convert")
    public Map<String, String> convert(@Valid @RequestBody Link link,
                                       @Value("${NUMBER_OF_SYMBOLS_OF_SHORT_LINK}") int length) {
        Link registeredLink = simpleLinkService
                .save(link, SiteLogin.getSiteLogin(), length);
        return Map.of(
                "code", registeredLink.getCode()
        );
    }

    @GetMapping("/redirect/{code}")
    public ResponseEntity<String> redirect(@PathVariable String code) {
        Link link = simpleLinkService
                .findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Code %s has no associations", code)));
        simpleLinkService.updateTotal(code);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .header(code, link.getUrl())
                .build();
    }

    @GetMapping("/statistic")
    public List<Map<String, String>> statistic() {
        List<Link> links = simpleLinkService
                .findAllBySite(SiteLogin.getSiteLogin());
        return links.stream()
                .map(x -> Map.of(
                        "url", x.getUrl(),
                        "total", String.valueOf(x.getTotal())
                ))
                .collect(Collectors.toList());
    }
}
