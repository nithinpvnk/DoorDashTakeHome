package com.techdining.www.doordashtakehome.dataSource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.techdining.www.doordashtakehome.models.RestaurantsNearBy
import io.reactivex.disposables.CompositeDisposable

class PagingDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val dataSource: NearByRestaurantsDataSource
) : DataSource.Factory<Int, RestaurantsNearBy>() {

    val nearByRestaurants = MutableLiveData<PagingDataSource>()

    override fun create(): DataSource<Int, RestaurantsNearBy> {
        val pagingDataSource = PagingDataSource(compositeDisposable, dataSource)
        nearByRestaurants.postValue(pagingDataSource)
        return pagingDataSource
    }


}