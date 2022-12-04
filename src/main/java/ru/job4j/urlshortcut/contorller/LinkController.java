package ru.job4j.urlshortcut.contorller;

import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.urlshortcut.model.Link;
import ru.job4j.urlshortcut.service.SimpleLinkService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/link")
@AllArgsConstructor
public class LinkController {

    private SimpleLinkService linkService;

    @GetMapping("/redirect/{code}")
    public ResponseEntity<String> redirect(@PathVariable String code) {
        Link link = linkService
                .findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Code %s has no associations", code)));
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .header(code, link.getUrl())
                .build();
    }

    @PostMapping("/convert")
    public Map<String, String> convert(@RequestBody Map<String, String> body) {
        Link link = new Link();
        link.setUrl(body.get("url"));
        link.setCode(RandomString.make(6));
        return new HashMap<>();
    }

    @GetMapping("/statistic")
    public Map<String, String> statistic() {
        return new HashMap<>();
    }
}
