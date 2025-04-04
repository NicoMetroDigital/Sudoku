package com.example.sudoku.repository

import com.example.sudoku.model.Highscore
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HighscoreRepository : JpaRepository<Highscore, Long>
