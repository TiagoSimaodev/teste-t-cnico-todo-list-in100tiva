package com.todolist.config;

import org.springframework.context.annotation.Bean;
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
     * Configura as regras de CORS.
     * @param registry Registro de configurações CORS.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Permite CORS para todos os endpoints
                .allowedOrigins("*")  // Permite todas as origens (em produção, especifique origens específicas)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Métodos HTTP permitidos
                .allowedHeaders("*")  // Permite todos os headers
                .allowCredentials(false);  // Não permite credenciais (cookies, authorization headers)
    }
}