package com.example.sudoku.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class SecurityConfig : WebMvcConfigurer {

    // CORS-Konfiguration
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**") // Alle Endpoints erlauben
            .allowedOrigins("http://localhost:5173") // Dein Frontend-Origin zulassen
            .allowedMethods("GET", "POST", "PUT", "DELETE") // Erlaubte HTTP-Methoden
            .allowedHeaders("*") // Erlaubte Header
            .allowCredentials(true) // Cookies/Anmeldeinformationen zulassen (falls benötigt)
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors {} // Aktiviert CORS
            .csrf { it.disable() } // Deaktiviert CSRF, da du eine API hast, die keine Sessions benötigt
            .authorizeHttpRequests { auth ->
                auth.requestMatchers("/api/**", "/sudoku/**").permitAll() // Endpoints öffentlich zugänglich
                    .anyRequest().authenticated() // Andere Endpoints erfordern Authentifizierung
            }
            .formLogin { it.disable() } // Deaktiviert das Login-Formular (falls nicht benötigt)
            .httpBasic { it.disable() } // Deaktiviert Basic Auth (optional)

        return http.build()
    }
}
