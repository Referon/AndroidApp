package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        val adapter = PostsAdapter(
            likeCallBack = {
                viewModel.likeById(it.id)
            },
            shareCallBack = {
                viewModel.shareById(it.id)
            }
        )

        binding.container.adapter = adapter


        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
    }

}
