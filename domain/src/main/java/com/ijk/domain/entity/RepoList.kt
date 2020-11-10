package com.ijk.domain.entity

data class RepoList(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<Items>
)

