package ru.netology.nmedia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.NMedia.db.AppDb
import ru.netology.nmedia.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositorySQLiteImpl

class PostViewModel(application: Application): AndroidViewModel(application) {

    private val empty = Post(
        0,
        "",
        "",
        "",
        null,
        false,
        0,
        0,
        0
    )
    private val repository: PostRepository = PostRepositorySQLiteImpl(AppDb.getInstance(application).postDao)

    val data = repository.getAll()
    val edited = MutableLiveData(empty)

    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun changeContent(content: String) {
        edited.value?.let {
            val text = content.trim()
            if (it.content == text) {
                return
            }
            edited.value = it.copy(content = text)
        }
    }

    fun likeById(id: Int) = repository.likeById(id)
    fun shareById(id: Int) = repository.shareById(id)
    fun removeById(id:Int) = repository.removeById(id)
    fun saveMessage(text: String?) = repository.saveMessage(text)
    fun getMessage(): String? = repository.getMessgae()
    fun deleteMessage() = repository.deleteMessage()
}