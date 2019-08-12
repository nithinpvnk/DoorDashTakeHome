package com.techdining.www.doordashtakehome.models

import androidx.recyclerview.widget.DiffUtil

data class RestaurantsNearBy(
    val address: Address,
    val cover_img_url: String,
    val delivery_fee: Int,
    val description: String,
    val id: Int,
    val name: String,
    val status: String
) {
    companion object {
        var DIFF_CALLBACK = object : DiffUtil.ItemCallback<RestaurantsNearBy>() {
            override fun areItemsTheSame(oldItem: RestaurantsNearBy, newItem: RestaurantsNearBy) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: RestaurantsNearBy, newItem: RestaurantsNearBy) =
                oldItem == newItem
        }
    }
}