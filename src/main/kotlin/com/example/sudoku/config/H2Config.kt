package com.example.sudoku.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableJpaRepositories(basePackages = arrayOf("com.example.sudoku.repository"))
@EnableTransactionManagement
public class H2Config {
    // Zusätzliche Konfigurationen können hier hinzugefügt werden, falls nötig
}
