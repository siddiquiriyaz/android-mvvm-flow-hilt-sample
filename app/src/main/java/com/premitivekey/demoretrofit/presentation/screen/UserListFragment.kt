package com.premitivekey.demoretrofit.presentation.screen

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.premitivekey.demoretrofit.R
import com.premitivekey.demoretrofit.databinding.FragmentUserListBinding
import com.premitivekey.demoretrofit.presentation.adapter.UserAdapter
import com.premitivekey.demoretrofit.presentation.base.UiState
import com.premitivekey.demoretrofit.presentation.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFragment : Fragment(R.layout.fragment_user_list) {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserViewModel by viewModels()

    private val adapter = UserAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentUserListBinding.bind(view)

        setupRecyclerView()
        observeState()
    }
    private fun setupRecyclerView() {
        binding.rvUser.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUser.adapter = adapter
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.usersState.collect { state ->
                    when (state) {
                        is UiState.Idle -> {
                            binding.progressBar.isVisible = false
                            binding.tvError.isVisible = false
                        }

                        is UiState.Loading -> {
                            binding.progressBar.isVisible = true
                            binding.tvError.isVisible = false
                        }

                        is UiState.Success -> {
                            binding.progressBar.isVisible = false
                            binding.tvError.isVisible = false
                            adapter.submitList(state.data)
                        }

                        is UiState.Error -> {
                            binding.progressBar.isVisible = false
                            binding.tvError.isVisible = true
                            binding.tvError.text = state.message
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}