package com.example.sudoku

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf().disable()  // Falls kein CSRF-Schutz benötigt wird
            .authorizeHttpRequests()
            .requestMatchers("/public/**").permitAll()  // Öffentliche Endpunkte
            .requestMatchers("/sudoku/**").permitAll()  // ❗ Sudoku-Endpunkte für alle zugänglich machen
            .anyRequest().authenticated()  // Andere Endpunkte erfordern Login
            .and()
            .formLogin()  // Aktiviert Standard-Login-Formular
            .and()
            .headers().frameOptions().disable()  // Für H2-Console

        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val user = User.withUsername("user")
            .password("{noop}password") // `{noop}` = Kein Passwort-Hashing
            .roles("USER")
            .build()

        return InMemoryUserDetailsManager(user)
    }
}
