package com.example.sudoku.controller

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

}
