package com.vanard.ovotask.ui.fragment.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vanard.ovotask.R
import com.vanard.ovotask.data.model.movie.MovieItem
import com.vanard.ovotask.databinding.ItemMoviesBinding

class PopularListAdapter : RecyclerView.Adapter<PopularListAdapter.ViewHolder>(){
    private lateinit var movieList:List<MovieItem>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemMoviesBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movies, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::movieList.isInitialized) movieList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    fun updateMovieList(item: List<MovieItem>){
        this.movieList = item
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemMoviesBinding): RecyclerView.ViewHolder(binding.root) {
        private val viewModel = MovieViewModel(binding.root.context)

        fun bind(item:MovieItem){
            viewModel.bind(item)
            binding.viewModel = viewModel
        }
    }

}