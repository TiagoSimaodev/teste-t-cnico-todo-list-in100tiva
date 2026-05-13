package com.todolist.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuração de CORS (Cross-Origin Resource Sharing) para a aplicação.
 * Permite que a API seja acessada por aplicações frontend rodando em diferentes origens.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Configura as regras de CORS para a aplicação.
     * Permite acesso de URLs específicas e também de localhost para desenvolvimento.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // URLs de produção (Vercel)
                .allowedOriginPatterns(
                        "https://to-do-lista-in100tiva.vercel.app",
                        "https://*.vercel.app"
                )
                .allowedOrigins(
                        "http://localhost:3000",
                        "http://localhost:8080",
                        "http://localhost:5173",
                        "http://127.0.0.1:3000"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD")
                .allowedHeaders("*")
                .exposedHeaders("Content-Length", "Content-Type")
                .allowCredentials(true)
                .maxAge(3600);
    }
}