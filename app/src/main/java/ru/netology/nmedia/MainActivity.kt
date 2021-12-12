package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            1,
            "Нетология. Университет интернет-профессий будущего",
            "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетинку. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растем сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остается с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть большее, целиться выше, бежать бысрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен -> http://netolo.gy/fyb",
            "21 мая в 18:36",
            false,
            0f,
            999f
        )

        with(binding) {
            author.text = post.author
            published.text = post.published
            message.text = post.content

            like.text = CounterService.counterWithRemains(post.likes)
            share.text = CounterService.counterWithRemains(post.shares)

            icLike.setOnClickListener {

                post.likedByMe = !post.likedByMe
                icLike.setImageResource(
                    if (post.likedByMe) R.drawable.ic_red_like_24 else R.drawable.ic_like_24
                )
                if (post.likedByMe) {

                    post.likes += 1f

                } else {

                    post.likes -= 1f

                }
                like.text = CounterService.counterWithRemains(post.likes)
            }

            icShare.setOnClickListener {

                post.shares += 1f
                share.text = CounterService.counterWithRemains(post.shares)
            }
        }
    }
}
