package com.techdining.www.doordashtakehome

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techdining.www.doordashtakehome.models.RestaurantsNearBy
import com.techdining.www.doordashtakehome.viewModel.NearByRestaurantsViewModel
import com.techdining.www.doordashtakehome.viewModel.NearByRestaurantsViewModelProvider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val loadingIndicator = findViewById<ProgressBar>(R.id.loading_indicator)
        val adapter = NearByRestaurantsAdapter(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_home)
        recyclerView.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(this)
        }

        val viewModelProvider = ViewModelProvider(
            this,
            NearByRestaurantsViewModelProvider()
        )[NearByRestaurantsViewModel::class.java]
        viewModelProvider.restaurantsList.observe(this, Observer<PagedList<RestaurantsNearBy>>
        {
            if(it != null) {
                adapter.submitList(it)
                recyclerView.visibility = View.VISIBLE
            }
        })

        viewModelProvider.progressLoadingStatus.observe(this, Observer<String> {
            when (it) {
                "Loaded" -> loadingIndicator.visibility = View.GONE
                "Loading" -> loadingIndicator.visibility = View.VISIBLE
                "LoadedProgress" -> loadingIndicator.visibility = View.GONE
                "LoadingProgress" -> loadingIndicator.visibility = View.VISIBLE
                else -> {
                    loadingIndicator.visibility = View.GONE
                    Toast.makeText(this, "Something is Wrong!!", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}
