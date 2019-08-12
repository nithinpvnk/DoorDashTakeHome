package com.techdining.www.doordashtakehome.models

data class Menu(
    val id: Int,
    val is_catering: Boolean,
    val name: String,
    val popular_items: List<Any>,
    val subtitle: String
)