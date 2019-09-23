package com.vanard.ovotask.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.vanard.ovotask.R
import com.vanard.ovotask.databinding.ActivityDetailMovieBinding
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movie)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        binding.viewModel = viewModel

        if (intent != null){
            viewModel.movie.value = intent.getParcelableExtra("data")
            viewModel.initData()
        }

        binding.detailBodyRecyclerViewTrailers.layoutManager = GridLayoutManager(this, 3)

        setSupportActionBar(detail_toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

    }
}