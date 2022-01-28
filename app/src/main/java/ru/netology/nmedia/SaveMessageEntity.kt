package ru.netology.nmedia

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SaveMessageEntity(
    @PrimaryKey
    val text: String
)
