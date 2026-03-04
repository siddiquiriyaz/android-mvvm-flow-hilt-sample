package com.premitivekey.demoretrofit.presentation.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.premitivekey.demoretrofit.R
import com.premitivekey.demoretrofit.databinding.FragmentAddUserBinding
import com.premitivekey.demoretrofit.presentation.base.UiState
import com.premitivekey.demoretrofit.presentation.viewModel.AddUserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AddUserFragment : Fragment(R.layout.fragment_add_user) {

    private val viewModel: AddUserViewModel by viewModels()
    private lateinit var binding: FragmentAddUserBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentAddUserBinding.bind(view)

        binding.btnSave.setOnClickListener {
            if (validateInput()) {

                val name = binding.etName.text.toString()
                val email = binding.etEmail.text.toString()

                viewModel.createUser(name, email)
            }
        }

        observeState()
    }

    private fun observeState() {

        viewLifecycleOwner.lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.createUserState.collect { state ->

                    when (state) {

                        is UiState.Idle -> {
                            binding.progressBar.isVisible = false
                        }
                        is UiState.Loading -> {
                            binding.progressBar.isVisible = true
                        }

                        is UiState.Success -> {

                            binding.progressBar.isVisible = false

                            Toast.makeText(
                                requireContext(),
                                "User Added",
                                Toast.LENGTH_SHORT
                            ).show()

                           // parentFragmentManager.popBackStack()
                        }

                        is UiState.Error -> {
                            binding.progressBar.isVisible = false
                            binding.tvError.text = state.message
                        }
                    }
                }
            }
        }
    }
    private fun validateInput(): Boolean {

        val name = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()

        binding.layoutName.error = null
        binding.layoutEmail.error = null

        if (name.isEmpty()) {
            binding.layoutName.error = "Name is required"
            return false
        }

        if (email.isEmpty()) {
            binding.layoutEmail.error = "Email is required"
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.layoutEmail.error = "Invalid email"
            return false
        }

        return true
    }
}