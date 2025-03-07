package com.example.sudoku.controller

import com.example.sudoku.service.SudokuService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class SudokuController(private val sudokuService: SudokuService) {

    // Endpunkt zum Erstellen eines neuen Sudokus
    @GetMapping("/api/sudoku/generate/{difficulty}")
    fun generateSudoku(@PathVariable difficulty: String): List<List<Int>> {
        return sudokuService.generateSudoku(difficulty)
    }

    // Endpunkt zum Lösen des Sudokus
    @GetMapping("/api/sudoku/solve")
    fun solveSudoku(grid: List<List<Int>>): List<List<Int>> {
        return sudokuService.solveSudoku(grid)
    }
}
