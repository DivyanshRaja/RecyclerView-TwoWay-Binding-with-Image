package com.raja.rv_with_databinding.data.model

data class User(
    val incomplete_results: Boolean,
    val items: ArrayList<Item>,
    val total_count: Int
)