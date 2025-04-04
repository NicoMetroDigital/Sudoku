package com.example.sudoku.model

import jakarta.persistence.*

@Entity
data class Highscore(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val playerName: String = "",

    val timeInSeconds: Int = 0
)
