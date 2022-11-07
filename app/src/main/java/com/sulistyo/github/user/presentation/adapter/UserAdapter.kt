package com.sulistyo.github.user.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sulistyo.github.user.core.domain.model.UserModel
import com.sulistyo.github.user.databinding.ItemUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val listUser = ArrayList<UserModel>()
    private var onItemClickCallback: OnItemCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(items: List<UserModel>) {
        listUser.clear()
        listUser.addAll(items)
        notifyDataSetChanged()
    }

    class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = listUser[position]
        Glide.with(holder.itemView.context)
            .load(user.avatarUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .circleCrop()
            .into(holder.binding.imgAvatar)
        holder.binding.tvUsername.text = user.login

        holder.itemView.setOnClickListener {
            onItemClickCallback?.onItemClicked(listUser[position])
        }
    }

    override fun getItemCount(): Int = listUser.size

    interface OnItemCallback {
        fun onItemClicked(data: UserModel)
    }
}
