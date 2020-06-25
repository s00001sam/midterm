package com.sam.sammidterm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sam.sammidterm.databinding.ItemHomeBinding

class HomeAdapter(val viewModel: HomeViewModel) : ListAdapter<Articles, HomeAdapter.HomeViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder.from(parent)
    }


    class HomeViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(articles: Articles, viewModel: HomeViewModel) {
            binding.viewModel = viewModel
            binding.articles = articles
            binding.textTime.text = articles.createdTime.toString()
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): HomeViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHomeBinding.inflate(layoutInflater, parent, false)
                return HomeViewHolder(binding)
            }
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<Articles>() {
        override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position), viewModel)
    }
}