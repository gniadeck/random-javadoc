package dev.gniadeck.randomjavadoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RandomJavadocApplication {

    public static void main(String[] args) {
        SpringApplication.run(RandomJavadocApplication.class, args);
    }

}
