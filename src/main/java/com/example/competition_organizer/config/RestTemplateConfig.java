<<<<<<<< HEAD:src/main/java/com/example/competition_organizer/config/RestTemplateConfig.java
package com.example.competition_organizer.config;
========
package competitionOrganizer.config;
>>>>>>>> upstream/main:src/main/java/competitionOrganizer/config/RestTemplateConfig.java

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
