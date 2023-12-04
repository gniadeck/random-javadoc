package dev.gniadeck.randomjavadoc.service;

import dev.gniadeck.randomjavadoc.model.JavaDocRef;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JavadocService {

    private final JavadocFetchingService fetchingService;
    private final RandomGenerator randomGenerator;

    public JavaDocRef randomJavadoc(Collection<String> allowedPackages) {
        var availableDocs = getAvailableDocs(allowedPackages);
        return availableDocs.get(randomGenerator.nextInt(availableDocs.size()));
    }

    public List<JavaDocRef> getModules() {
        return fetchingService.getAllModules();
    }

    private List<JavaDocRef> getAvailableDocs(Collection<String> allowedModules) {
        var allDocs = fetchingService.getAllClassesAndInterfaces();
        if(allowedModules.isEmpty()) {
            return allDocs;
        } else {
            return allDocs.stream()
                    .filter(doc -> isInModule(doc, allowedModules))
                    .collect(Collectors.toList());
        }
    }

    private boolean isInModule(JavaDocRef doc, Collection<String> allowedModules) {
        return allowedModules.stream()
                .anyMatch(module -> doc.url().contains(module));
    }
}
