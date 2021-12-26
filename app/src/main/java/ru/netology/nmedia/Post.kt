package ru.netology.nmedia

data class Post(
   val id: Int,
   val author: String,
   val content: String,
   val published: String,
   val likedByMe: Boolean,
   val shares: Int,
   val likes: Int
) {
}
