package com.example.sudoku.service

import com.example.sudoku.model.Highscore
import com.example.sudoku.repository.HighscoreRepository
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class SudokuService(
    private val highscoreRepository: HighscoreRepository
) {

    fun generateSudoku(difficulty: String): List<List<Int>> {
        val fullGrid = MutableList(9) { MutableList(9) { 0 } }
        solve(fullGrid)
        removeNumbers(fullGrid, difficulty)
        return fullGrid.map { it.toList() }
    }

    private fun removeNumbers(grid: MutableList<MutableList<Int>>, difficulty: String) {
        val removeCount = when (difficulty.lowercase()) {
            "easy" -> 30
            "medium" -> 40
            "hard" -> 50
            else -> 40
        }
        repeat(removeCount) {
            var row: Int
            var col: Int
            do {
                row = Random.nextInt(9)
                col = Random.nextInt(9)
            } while (grid[row][col] == 0)
            grid[row][col] = 0
        }
    }

    fun solveSudoku(grid: List<List<Int>>): List<List<Int>> {
        val mutableGrid = grid.map { it.toMutableList() }.toMutableList()
        return if (solve(mutableGrid)) mutableGrid.map { it.toList() } else grid
    }

    private fun solve(grid: MutableList<MutableList<Int>>): Boolean {
        for (row in 0 until 9) {
            for (col in 0 until 9) {
                if (grid[row][col] == 0) {
                    for (num in 1..9) {
                        if (isValid(grid, row, col, num)) {
                            grid[row][col] = num
                            if (solve(grid)) return true
                            grid[row][col] = 0
                        }
                    }
                    return false
                }
            }
        }
        return true
    }

    private fun isValid(grid: MutableList<MutableList<Int>>, row: Int, col: Int, num: Int): Boolean {
        for (i in 0 until 9) {
            if (grid[row][i] == num || grid[i][col] == num) return false
        }
        val startRow = (row / 3) * 3
        val startCol = (col / 3) * 3
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (grid[startRow + i][startCol + j] == num) return false
            }
        }
        return true
    }

    // 🔥 Highscore speichern
    fun saveHighscore(playerName: String, timeInSeconds: Int) {
        val highscore = Highscore(playerName = playerName, timeInSeconds = timeInSeconds)
        highscoreRepository.save(highscore)
    }

    // 📊 Highscores abrufen
    fun getHighscores(): List<Highscore> {
        return highscoreRepository.findAll()
            .sortedBy { it.timeInSeconds } // Optional: nach Zeit sortieren
    }
}
