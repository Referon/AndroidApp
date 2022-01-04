package ru.netology.nmedia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.databinding.CardPostBinding

typealias LikeCallBack = (post: Post) -> Unit
typealias ShareCallBack = (post: Post) -> Unit

class PostsAdapter (
    val shareCallBack: ShareCallBack,
    val likeCallBack: LikeCallBack,

    ) : androidx.recyclerview.widget.ListAdapter<Post, PostViewHolder>(PostDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(binding,shareCallBack, likeCallBack)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

}


class PostViewHolder(
    private val binding: CardPostBinding,
    private val shareCallBack: ShareCallBack,
    private val likeCallBack: LikeCallBack
): RecyclerView.ViewHolder(binding.root) {

    fun bind (post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            message.text = post.content
            like.text = CounterService.counterWithRemains(post.likes)
            share.text = CounterService.counterWithRemains(post.shares)

            icLike.setImageResource(
                if (post.likedByMe) R.drawable.ic_red_like_24 else R.drawable.ic_like_24
            )
            icLike.setOnClickListener{

                likeCallBack(post)
            }
            icShare.setOnClickListener {

                shareCallBack(post)
            }
        }
    }
}

class PostDiffCallback: DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}

