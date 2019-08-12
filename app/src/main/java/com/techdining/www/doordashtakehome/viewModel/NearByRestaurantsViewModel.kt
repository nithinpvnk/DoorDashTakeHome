package com.techdining.www.doordashtakehome.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.techdining.www.doordashtakehome.dataSource.NearByRestaurantsDataSource
import com.techdining.www.doordashtakehome.dataSource.PagingDataSource
import com.techdining.www.doordashtakehome.dataSource.PagingDataSourceFactory
import com.techdining.www.doordashtakehome.models.RestaurantsNearBy
import io.reactivex.disposables.CompositeDisposable

class NearByRestaurantsViewModel : ViewModel() {
    private var compositeDisposable = CompositeDisposable()
    private val sourceFactory: PagingDataSourceFactory
    var restaurantsList: LiveData<PagedList<RestaurantsNearBy>>
    var progressLoadingStatus: LiveData<String> = MutableLiveData()

    init {
        sourceFactory = PagingDataSourceFactory(compositeDisposable, NearByRestaurantsDataSource())
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(10)
            .setEnablePlaceholders(true)
            .build()
        restaurantsList = LivePagedListBuilder(sourceFactory, config).build()
        progressLoadingStatus =
            Transformations.switchMap(sourceFactory.nearByRestaurants, PagingDataSource::progressLiveStatus)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}
