package dev.gniadeck.randomjavadoc.service;

import dev.gniadeck.randomjavadoc.http.HttpClient;
import dev.gniadeck.randomjavadoc.model.JavaDocRef;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JavadocFetchingService {

    @Value("${javadoc.baseUrl}")
    private String baseUrl;
    @Value("${javadoc.indexSuffix}")
    private String indexSuffix;
    @Value("${javadoc.modulesSuffix}")
    private String modulesSuffix;

    private final HttpClient httpClient;
    private final JavadocResponseParsingService parsingService;
    private static final String CACHE_PREFIX = "JAVADOC-FETCHING-SERVICE-";

    @Cacheable(CACHE_PREFIX + "CLASSES-AND-INTERFACES")
    public List<JavaDocRef> getAllClassesAndInterfaces() {
        var indexUrl = baseUrl + indexSuffix;
        return parsingService.parseJavadocTable(httpClient.get(indexUrl));
    }

    @Cacheable(CACHE_PREFIX + "MODULES")
    public List<JavaDocRef> getAllModules() {
        var modulesUrl = baseUrl + modulesSuffix;
        return parsingService.parseJavadocTable(httpClient.get(modulesUrl));
    }

}
