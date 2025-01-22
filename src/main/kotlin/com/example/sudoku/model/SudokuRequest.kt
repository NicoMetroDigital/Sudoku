
package com.example.sudoku.model

data class SudokuRequest(
    val grid: List<List<Int>>, // Hier muss die grid-Eigenschaft definiert sein
    val difficulty: String // Beispiel für ein weiteres Feld, falls benötigt
)
