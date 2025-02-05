package com.example.sudoku.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
class CorsConfig {

    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()

        config.allowCredentials = true // Erlaubt Cookies & Authentifizierung

        // ❗ Hier nicht "*" verwenden, sondern die genaue Frontend-URL
        config.addAllowedOrigin("http://localhost:3000")

        config.addAllowedHeader("*") // Erlaubt alle Header
        config.addAllowedMethod("*") // Erlaubt GET, POST, PUT, DELETE etc.
        config.addAllowedMethod("OPTIONS") // Erlaubt Preflight-Anfragen

        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }
}
