package com.premitivekey.demoretrofit.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.premitivekey.demoretrofit.data.model.UserDto
import com.premitivekey.demoretrofit.databinding.ItemUserBinding

class UserAdapter : ListAdapter<UserDto, UserAdapter.UserViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<UserDto>() {
        override fun areItemsTheSame(oldItem: UserDto, newItem: UserDto): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UserDto, newItem: UserDto): Boolean =
            oldItem == newItem
    }

    inner class UserViewHolder(
        private val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserDto) {
            binding.tvName.text = user.name
            binding.tvCompany.text = user.company?.name
            binding.tvEmail.text = user.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}