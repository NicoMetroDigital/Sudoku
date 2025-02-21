package com.example.sudoku.service

import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class SudokuService {

    fun generateSudoku(difficulty: String): List<List<Int>> {
        val fullGrid = MutableList(9) { MutableList(9) { 0 } } // Richtiger Typ
        solve(fullGrid) // Erst ein vollständiges Sudoku erzeugen
        removeNumbers(fullGrid, difficulty) // Zahlen entfernen, um Schwierigkeit anzupassen
        return fullGrid.map { it.toList() } // Unveränderliche Liste zurückgeben
    }

    private fun removeNumbers(grid: MutableList<MutableList<Int>>, difficulty: String) {
        val removeCount = when (difficulty.lowercase()) {
            "easy" -> 30
            "medium" -> 40
            "hard" -> 50
            else -> 40 // Standard auf "Medium"
        }
        repeat(removeCount) {
            var row: Int
            var col: Int
            do {
                row = Random.nextInt(9)
                col = Random.nextInt(9)
            } while (grid[row][col] == 0) // Stelle sicher, dass nicht mehrfach dieselbe Zahl entfernt wird
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
                            grid[row][col] = 0 // Backtracking
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
}
