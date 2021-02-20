package com.aldidwiki.myquizapp.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_entity")
data class QuestionEntity(
//        @PrimaryKey(autoGenerate = true) val id: Int,
        @PrimaryKey val question: String,
        val correctAnswer: String,
        val isCorrect: Boolean
)