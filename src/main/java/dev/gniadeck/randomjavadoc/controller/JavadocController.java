package dev.gniadeck.randomjavadoc.controller;

import dev.gniadeck.randomjavadoc.model.JavaDocRef;
import dev.gniadeck.randomjavadoc.service.JavadocService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/javadoc")
public class JavadocController {

    private final JavadocService javadocService;

    @GetMapping("/random")
    public JavaDocRef randomJavadoc(@RequestParam Optional<Collection<String>> modules) {
        return javadocService.randomJavadoc(modules.orElse(Collections.emptyList()));
    }

    @GetMapping("/modules")
    public List<JavaDocRef> getModules() {
        return javadocService.getModules();
    }

}
