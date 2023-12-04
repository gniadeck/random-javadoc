package dev.gniadeck.randomjavadoc.service;

import dev.gniadeck.randomjavadoc.model.JavaDocRef;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JavadocResponseParsingService {

    @Value("${javadoc.baseUrl}")
    private String baseUrl;

    public List<JavaDocRef> parseJavadocTable(String indexContent) {
        var indexDocument = Jsoup.parse(indexContent);
        var classDivs = indexDocument.getElementsByClass("col-first");
        removeHeader(classDivs);
        List<JavaDocRef> result = new ArrayList<>();
        for(var classDiv : classDivs) {
            var refUrl = classDiv.getElementsByTag("a").first().attr("href");
            var className = classDiv.text();
            result.add(new JavaDocRef(className, baseUrl + refUrl));
        }
        return result;
    }

    private void removeHeader(Elements classDivs) {
        classDivs.remove(0);
    }

}
