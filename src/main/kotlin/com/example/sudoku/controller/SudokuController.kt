package com.example.sudoku.controller

import com.example.sudoku.model.Highscore
import com.example.sudoku.model.SudokuRequest
import com.example.sudoku.service.SudokuService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sudoku")
class SudokuController(val sudokuService: SudokuService) {

    @GetMapping("/new")
    fun getNewSudoku(@RequestParam difficulty: String): List<List<Int>> {
        return sudokuService.generateSudoku(difficulty)
    }

    @PostMapping("/solve")
    fun solveSudoku(@RequestBody request: SudokuRequest): List<List<Int>> {
        return sudokuService.solveSudoku(request.grid)
    }

    @GetMapping("/highscore")
    fun getHighscores(): List<Highscore> {
        return sudokuService.getHighscores()
    }

    @PostMapping("/highscore")
    fun saveHighscore(@RequestBody highscore: Highscore): String {
        sudokuService.saveHighscore(highscore)
        return "Highscore saved!"
    }
}
