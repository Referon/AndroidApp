package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import ru.netology.nmedia.Post
import ru.netology.nmedia.PostEntity
import ru.netology.nmedia.dao.PostDao

class PostRepositoryImpl(private val dao: PostDao) : PostRepository {

    override fun getAll(): LiveData<List<Post>> = dao.getAll().map { list -> list.map {it.toDto()} }

    override fun likeById(id: Int) = dao.likeById(id)

    override fun shareById(id: Int) = dao.shareById(id)

    override fun removeById(id: Int) = dao.removeById(id)

    override fun save(post: Post) = dao.save(PostEntity.fromDto(post))

    override fun saveMessage(text: String?) = dao.saveMessgae(text)

    override fun getMessgae(): String? = dao.getMessage()

    override fun deleteMessage() = dao.deleteMessage()

}
