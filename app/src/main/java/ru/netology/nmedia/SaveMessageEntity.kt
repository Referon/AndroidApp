package ru.netology.nmedia

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SaveMessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val text: String
)
