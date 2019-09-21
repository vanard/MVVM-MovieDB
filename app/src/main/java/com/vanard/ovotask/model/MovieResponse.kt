package com.vanard.ovotask.model

data class MovieResponse(
	val page: Int? = null,
	val totalPages: Int? = null,
	val results: List<ResultsItem?>? = null,
	val totalResults: Int? = null
)
