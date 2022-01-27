package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.Post
import ru.netology.nmedia.dao.PostDao

class PostRepositorySQLiteImpl(private val dao: PostDao) : PostRepository {

    private var posts = emptyList<Post>()
    private val data = MutableLiveData(posts)

    init {
        posts =dao.getAll()
        data.value = posts
    }
    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Int) {
        dao.likeById(id)
        posts = posts.map {
            if (it.id != id) it else if (!it.likedByMe) it.copy(
                likedByMe = !it.likedByMe,
                likes = it.likes + 1
            ) else it.copy(likedByMe = !it.likedByMe, likes = it.likes - 1)
        }
        data.value = posts
    }

    override fun shareById(id: Int) {
        dao.shareById(id)
        posts = posts.map {
            if (it.id == id) it.copy(shares = it.shares + 1) else it
        }
        data.value = posts
    }

    override fun removeById(id: Int) {
        dao.removeById(id)
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun save(post: Post) {
        val id = post.id
        val saved = dao.save(post)
        posts = if(id == 0) {
            listOf(saved) + posts
        }else {
            posts.map {
                if (it.id != id) it else saved
            }
        }
        data.value = posts
    }

    override fun saveMessage(text: String?) =
        dao.saveMessgae(text)


    override fun getMessgae(): String? =
        dao.getMessage()

    override fun deleteMessage() = dao.deleteMessage()
}
