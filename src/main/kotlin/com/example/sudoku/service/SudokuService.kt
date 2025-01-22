package com.example.sudoku.service

import com.example.sudoku.model.Highscore
import com.example.sudoku.repository.HighscoreRepository
import org.springframework.stereotype.Service

@Service
class SudokuService(private val highscoreRepository: HighscoreRepository) {

    fun solveSudoku(grid: List<List<Int>>): List<List<Int>> {
        // Hier kommt die Logik, um das Sudoku zu lösen
        return grid // Dies ist nur ein Platzhalter. Hier solltest du das gelöste Sudoku zurückgeben.
    }

    fun generateSudoku(difficulty: String): List<List<Int>> {
        // Generiere ein neues Sudoku basierend auf der Schwierigkeit
        return List(9) { List(9) { 0 } } // Beispiel für ein leeres Sudoku
    }

    fun getHighscores(): List<Highscore> {
        return highscoreRepository.getAll() // Alle gespeicherten Highscores zurückgeben
    }

    fun saveHighscore(highscore: Highscore) {
        highscoreRepository.save(highscore) // Highscore speichern
    }
}
