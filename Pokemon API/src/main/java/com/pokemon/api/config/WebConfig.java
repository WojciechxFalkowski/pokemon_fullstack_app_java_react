package com.pokemon.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")  // ścieżka do endpointów
                        .allowedOrigins("http://localhost:7010")  // adres frontendu
                        .allowedMethods("GET", "POST", "PUT", "DELETE")  // metody HTTP
                        .allowedHeaders("*");  // wszystkie nagłówki
            }
        };
    }
}
