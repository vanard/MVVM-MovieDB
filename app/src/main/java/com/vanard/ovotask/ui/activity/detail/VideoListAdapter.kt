package com.vanard.ovotask.ui.activity.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vanard.ovotask.R
import com.vanard.ovotask.data.model.video.Video
import com.vanard.ovotask.databinding.ItemVideoBinding

class VideoListAdapter : RecyclerView.Adapter<VideoListAdapter.ViewHolder>(){
    private lateinit var movieList:List<Video>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: ItemVideoBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_video, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::movieList.isInitialized) movieList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    fun updateVideoList(item: List<Video>){
        this.movieList = item
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemVideoBinding): RecyclerView.ViewHolder(binding.root) {
        private val viewModel = VideoViewModel()

        fun bind(item: Video){
            viewModel.bind(item)
            binding.viewModel = viewModel
        }
    }

}