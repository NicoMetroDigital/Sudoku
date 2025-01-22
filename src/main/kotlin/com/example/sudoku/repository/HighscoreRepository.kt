package com.example.sudoku.repository

import com.example.sudoku.model.Highscore
import org.springframework.stereotype.Repository

@Repository
class HighscoreRepository {
    private val highscores = mutableListOf<Highscore>()

    fun getAll(): List<Highscore> = highscores

    fun save(highscore: Highscore) {
        highscores.add(highscore)
    }
}
