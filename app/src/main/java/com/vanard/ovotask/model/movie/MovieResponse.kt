package com.vanard.ovotask.model.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val page: Int? = null,
    @SerializedName("total_pages")
    val total_pages: Int? = null,
    val results: List<ResultsItem?>? = null,
    @SerializedName("total_results")
    val total_results: Int? = null
)
