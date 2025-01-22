package com.example.sudoku.model

data class Highscore(
    val playerName: String,
    val score: Int,
    val date: String // Zum Beispiel das Datum, an dem der Highscore erreicht wurde
)
