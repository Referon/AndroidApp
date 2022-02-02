package ru.netology.nmedia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.databinding.FragmentNewPostBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class NewPostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNewPostBinding.inflate(inflater, container, false)

        val viewModel: PostViewModel by viewModels(ownerProducer = ::requireParentFragment)

        arguments?.textArg?.let { binding.content.setText(it) }

        binding.content.requestFocus()

        if (binding.content.text.isBlank() && viewModel.getMessage() != null) {
            viewModel.getMessage()?.let(binding.content::setText)
        }

        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {

            if (arguments?.textArg.toString() != binding.content.text.toString() && viewModel.getMessage() != binding.content.text.toString()) {
                viewModel.saveMessage(binding.content.text.toString())
            }
            remove()
            viewModel.cancel()
            findNavController().navigateUp()
        }
        binding.save.setOnClickListener {
            val text = binding.content.text.toString()

            if (text.isNotBlank()) {
                viewModel.changeContent(text)
                viewModel.save()
            }
            viewModel.deleteMessage()
            findNavController().navigateUp()
        }

        return binding.root
    }

    companion object {
        var Bundle.textArg: String? by StringArg
    }
}