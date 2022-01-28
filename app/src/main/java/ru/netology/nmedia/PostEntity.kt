package ru.netology.nmedia

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val author: String,
    val content: String,
    val published: String,
    val video: String? = null,
    val likedByMe: Boolean,
    val shares: Int,
    val likes: Int,
    val view: Int
) {
    fun toDto() = Post(id, author, content, published, video, likedByMe, shares, likes, view)

    companion object {
        fun fromDto(post: Post) = with(post) {
            PostEntity(id, author, content, published, video, likedByMe, shares, likes, view)
        }
    }
}
