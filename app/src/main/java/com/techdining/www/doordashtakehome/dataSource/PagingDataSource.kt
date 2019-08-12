package com.techdining.www.doordashtakehome.dataSource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.techdining.www.doordashtakehome.models.RestaurantsNearBy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PagingDataSource(private val compositeDisposable: CompositeDisposable,
                       private val dataSource : NearByRestaurantsDataSource
) : PageKeyedDataSource<Int, RestaurantsNearBy>() {

    val progressLiveStatus: MutableLiveData<String> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, RestaurantsNearBy>) {
        progressLiveStatus.postValue("Loading")
        compositeDisposable.add(dataSource.searchResult.searchNearByRestaurants(
            lat = "37.422740",
            lng = "-122.139956",
            offset = 0,
            limit = 100
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { result ->
                    progressLiveStatus.postValue("Loaded")
                    callback.onResult(result, null, 100)
                    Log.e("TESTING", "${result.size} ")
                },
                { error ->
                    error.printStackTrace()
                }
            ))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RestaurantsNearBy>) {
        progressLiveStatus.postValue("LoadingProgress")
        compositeDisposable.add(dataSource.searchResult.searchNearByRestaurants(
            lat = "37.422740",
            lng = "-122.139956",
            offset = params.key,
            limit = 100
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { result ->
                    progressLiveStatus.postValue("LoadedProgress")
                    callback.onResult(result, params.key + 1)
                    Log.e("TESTING", "${result.size} + ${params.key+1}")
                },
                { error ->
                    error.printStackTrace()
                }
            ))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RestaurantsNearBy>) {
    }


}