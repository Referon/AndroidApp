package ru.netology.nmedia

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.NewPostFragment.Companion.textArg
import ru.netology.nmedia.databinding.FragmentFeedBinding
import ru.netology.nmedia.viewmodel.PostViewModel
import ru.netology.nmedia.OnInteractionListener as OnInteractionListener

class FeedFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFeedBinding.inflate(inflater, container, false)

        val viewModel: PostViewModel by viewModels(ownerProducer = ::requireParentFragment)


        val adapter = PostsAdapter(object : OnInteractionListener {

            override fun onEdit(post: Post) {
                viewModel.edit(post)

                findNavController().navigate(
                    R.id.action_feedFragment_to_newPostFragment,
                    Bundle().apply {
                        textArg = post.content
                    }
                )

            }

            override fun onVideo(post: Post) {

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.video))
                startActivity(intent)
            }

            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.shareById(post.id)
            }

            override fun onPost(post: Post) {
                findNavController().navigate(
                    R.id.action_feedFragment_to_postFragment,
                    Bundle().apply {
                        idArg = post.id
                    }
                )
            }
        })

        binding.list.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner) { posts ->
            val newPost = adapter.itemCount < posts.size
            adapter.submitList(posts) {
                if (newPost) {
                    binding.list.smoothScrollToPosition(0)
                }
            }
        }

        binding.addPost.setOnClickListener {

            findNavController().navigate(R.id.action_feedFragment_to_newPostFragment)
        }


        return binding.root
    }

    companion object {
        var Bundle.idArg: Int by IntArg
    }

}