package com.example.sudoku.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors { } // ✅ Erlaubt Cross-Origin Requests
            .csrf { it.disable() } // ✅ Deaktiviert CSRF für APIs (WICHTIG für Frontend-Zugriff)
            .authorizeHttpRequests { auth ->
                auth.requestMatchers("/api/**", "/sudoku/**").permitAll() // 🔓 Erlaubt Zugriff auf Sudoku-Endpoints
                    .anyRequest().authenticated() // 🔐 Schützt alles andere
            }
            .formLogin { it.disable() } // 🚫 Deaktiviert Login-Formular von Spring Security
            .httpBasic { it.disable() } // 🚫 Deaktiviert Basic Auth (optional)

        return http.build()
    }
}
