package com.vanard.ovotask.ui.fragment.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vanard.ovotask.R
import com.vanard.ovotask.data.model.Favorite
import com.vanard.ovotask.databinding.ItemFavoriteBinding

class FavoriteListAdapter : RecyclerView.Adapter<FavoriteListAdapter.ViewHolder>(){
    private lateinit var movieList:List<Favorite>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemFavoriteBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movies, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::movieList.isInitialized) movieList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    fun updateMovieList(item: List<Favorite>){
        this.movieList = item
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemFavoriteBinding): RecyclerView.ViewHolder(binding.root) {
        private val viewModel = ItemFavoriteViewModel(binding.root.context)

        fun bind(item: Favorite){
            viewModel.bind(item)
            binding.viewModel = viewModel
        }
    }

}