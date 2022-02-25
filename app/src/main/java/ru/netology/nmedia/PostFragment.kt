package ru.netology.nmedia

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.FeedFragment.Companion.idArg
import ru.netology.nmedia.NewPostFragment.Companion.textArg
import ru.netology.nmedia.databinding.FragmentPostBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class PostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPostBinding.inflate(inflater, container, false)

        val viewModel: PostViewModel by viewModels(ownerProducer = ::requireParentFragment)

        val id = arguments?.idArg

        viewModel.data.observe(viewLifecycleOwner) { posts ->

            binding.postContent.apply {
                posts.map { post ->
                    if (post.id == id) {

                        author.text = post.author
                        published.text = post.published
                        message.text = post.content
                        icViews.text = CounterService.counterWithRemains(post.view)
                        icLike.text = CounterService.counterWithRemains(post.likes)
                        icShare.text = CounterService.counterWithRemains(post.shares)
                        icLike.isChecked = post.likedByMe

                        if (post.video == null) {
                            binding.postContent.videoGroup.visibility = View.GONE
                        }

                        options.setOnClickListener {
                            PopupMenu(it.context, it).apply {
                                inflate(R.menu.menu_post)
                                setOnMenuItemClickListener { item ->
                                    when (item.itemId) {
                                        R.id.edit -> {

                                            viewModel.edit(post)
                                            findNavController().navigate(
                                                R.id.action_postFragment_to_newPostFragment,
                                                Bundle().apply {
                                                    textArg = post.content
                                                }
                                            )
                                            true
                                        }
                                        R.id.remove -> {
                                            findNavController().navigate(
                                                R.id.action_postFragment_to_feedFragment
                                            )
                                            viewModel.removeById(post.id)
                                            true
                                        }
                                        else -> false
                                    }
                                }
                            }.show()
                        }
                        icLike.setOnClickListener {
                            viewModel.likeById(post.id)
                        }

                        icShare.setOnClickListener {
                            viewModel.shareById(post.id)
                        }

                        videoCover.setOnClickListener {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.video))
                            startActivity(intent)
                        }
                        icPlay.setOnClickListener {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.video))
                            startActivity(intent)
                        }

                    }
                }
            }
        }



        return binding.root
    }
}