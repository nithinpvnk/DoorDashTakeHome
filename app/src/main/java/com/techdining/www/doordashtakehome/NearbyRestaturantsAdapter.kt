package com.techdining.www.doordashtakehome

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.techdining.www.doordashtakehome.models.RestaurantsNearBy
import kotlinx.android.synthetic.main.recycler_view_item_view.view.*

class NearByRestaurantsAdapter(private val context: Context) :
    PagedListAdapter<RestaurantsNearBy, NearByRestaurantsAdapter.NearByViewHolder>(RestaurantsNearBy.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NearByViewHolder {
        return  NearByViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_item_view, parent, false))
    }

    override fun onBindViewHolder(holder: NearByViewHolder, position: Int) {
        Glide.with(holder.itemView.restaurant_image.context)
            .load(getItem(position)?.cover_img_url).transition(DrawableTransitionOptions.withCrossFade())
            .diskCacheStrategy(
                DiskCacheStrategy.ALL
            )
            .into(holder.itemView.restaurant_image)
        holder.itemView.restaurant_name.text = getItem(position)?.name
        holder.itemView.restaurant_cusine.text = getItem(position)?.description
        holder.itemView.restaurant_distance.text = (getItem(position)?.status + " wait")
        holder.itemView.restaurant_location.text = getItem(position)?.address?.street
        holder.itemView.restaurant_delivery_fee.text =
            context.resources.getString(R.string.deliveryFees, getItem(position)?.delivery_fee?.toString())
    }

    class NearByViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
