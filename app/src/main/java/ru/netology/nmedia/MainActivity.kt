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
        viewModel.data.observe(this) { post->

            with(binding) {
                author.text = post.author
                published.text = post.published
                message.text = post.content

                like.text = CounterService.counterWithRemains(post.likes)
                share.text = CounterService.counterWithRemains(post.shares)

                icLike.setImageResource(
                    if (post.likedByMe) R.drawable.ic_red_like_24 else R.drawable.ic_like_24
                )
                like.text = CounterService.counterWithRemains(post.likes)

                icLike.setOnClickListener {
                    viewModel.like()
                }

                icShare.setOnClickListener {
                    viewModel.share()
                }
            }
        }
    }
}
