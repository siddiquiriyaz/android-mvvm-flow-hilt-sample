package com.premitivekey.demoretrofit.presentation.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.premitivekey.demoretrofit.R
import com.premitivekey.demoretrofit.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)

        binding.btnGetUsers.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, UserListFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.btnAddUser.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AddUserFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}