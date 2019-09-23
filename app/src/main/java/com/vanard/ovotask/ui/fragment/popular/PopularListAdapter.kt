package com.vanard.ovotask.ui.fragment.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vanard.ovotask.R
import com.vanard.ovotask.databinding.ItemMoviesBinding
import com.vanard.ovotask.model.movie.ResultsItem
import com.vanard.ovotask.ui.fragment.item.MovieViewModel

class PopularListAdapter : RecyclerView.Adapter<PopularListAdapter.ViewHolder>(){
    private lateinit var movieList:List<ResultsItem>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularListAdapter.ViewHolder {
        val binding: ItemMoviesBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movies, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::movieList.isInitialized) movieList.size else 0
    }

    override fun onBindViewHolder(holder: PopularListAdapter.ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    fun updateMovieList(item: List<ResultsItem>){
        this.movieList = item
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemMoviesBinding): RecyclerView.ViewHolder(binding.root) {
        private val viewModel = MovieViewModel()

        fun bind(item:ResultsItem){
            viewModel.bind(item)
            binding.viewModel = viewModel
        }
    }

}