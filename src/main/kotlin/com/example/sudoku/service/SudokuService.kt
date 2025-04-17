package com.example.sudoku.service

import com.example.sudoku.model.Highscore
import com.example.sudoku.repository.HighscoreRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class SudokuService(
    private val highscoreRepository: HighscoreRepository
) {

    private val logger = LoggerFactory.getLogger(SudokuService::class.java)

    fun generateSudoku(difficulty: String): List<List<Int>> {
        val fullGrid = MutableList(9) { MutableList(9) { 0 } }

        if (!solve(fullGrid)) {
            logger.error("Konnte vollständiges Sudoku-Grid nicht generieren!")
            throw IllegalStateException("Fehler beim Erzeugen des Sudoku-Grids")
        }

        removeNumbers(fullGrid, difficulty)
        logger.info("✅ Sudoku erzeugt für Schwierigkeit: $difficulty")
        return fullGrid.map { it.toList() }
    }

    private fun removeNumbers(grid: MutableList<MutableList<Int>>, difficulty: String) {
        val removeCount = when (difficulty.lowercase()) {
            "easy" -> 30
            "medium" -> 40
            "hard" -> 50
            else -> {
                logger.warn("⚠️ Unbekannte Schwierigkeit '$difficulty', nutze Standard (medium)")
                40
            }
        }

        var removed = 0
        var attempts = 0
        val attemptsLimit = 1000

        while (removed < removeCount && attempts < attemptsLimit) {
            val row = Random.nextInt(9)
            val col = Random.nextInt(9)

            if (grid[row][col] != 0) {
                grid[row][col] = 0
                removed++
            }

            attempts++
        }

        logger.info("🧩 $removed Zahlen entfernt für '$difficulty' (nach $attempts Versuchen)")
    }

    fun solveSudoku(grid: List<List<Int>>): List<List<Int>> {
        val mutableGrid = grid.map { it.toMutableList() }.toMutableList()

        return if (solve(mutableGrid)) {
            logger.info("✅ Sudoku gelöst")
            mutableGrid.map { it.toList() }
        } else {
            logger.warn("❌ Sudoku konnte nicht gelöst werden")
            grid
        }
    }

    private fun solve(grid: MutableList<MutableList<Int>>): Boolean {
        for (row in 0..8) {
            for (col in 0..8) {
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

    private fun isValid(grid: List<List<Int>>, row: Int, col: Int, num: Int): Boolean {
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

    fun saveHighscore(playerName: String, timeInSeconds: Int) {
        val cleanName = playerName.trim().take(30)

        when {
            cleanName.isBlank() -> {
                logger.warn("❌ Highscore abgelehnt – Name ist leer oder ungültig")
                return
            }

            timeInSeconds < 0 -> {
                logger.warn("❌ Highscore abgelehnt – Zeit ist negativ ($timeInSeconds)")
                return
            }

            else -> {
                val highscore = Highscore(playerName = cleanName, timeInSeconds = timeInSeconds)
                highscoreRepository.save(highscore)
                logger.info("🏆 Highscore gespeichert: $cleanName – $timeInSeconds Sekunden")
            }
        }
    }

    fun getHighscores(): List<Highscore> {
        val list = highscoreRepository.findAll().sortedBy { it.timeInSeconds }
        logger.info("📊 ${list.size} Highscores geladen")
        return list
    }
}
