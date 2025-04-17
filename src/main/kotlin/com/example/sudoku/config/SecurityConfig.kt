package com.example.sudoku.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun corsFilter(): CorsFilter {
        val config = CorsConfiguration().apply {
            allowedOrigins = listOf(
                "http://localhost:5173",
                "http://0.0.0.0:5173",
                "http://127.0.0.1:5173"
            )
            allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
            allowedHeaders = listOf("*")
            allowCredentials = true
            exposedHeaders = listOf("Authorization", "Content-Type") // Falls du Tokens schickst
        }

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() } // CSRF deaktivieren (für APIs okay)
            .cors { } // ⬅️ Aktiviert den CorsFilter oben
            .authorizeHttpRequests {
                it.anyRequest().permitAll() // Alles erlauben – du kannst später feiner einstellen
            }

        return http.build()
    }
}
