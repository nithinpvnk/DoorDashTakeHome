package com.techdining.www.doordashtakehome

import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.techdining.www.doordashtakehome.models.RestaurantsNearBy
import com.techdining.www.doordashtakehome.network.NearByRestaurantsApiService
import io.reactivex.Observable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test

class NearByRestaurantsServiceTest {
    //  val apiService = mock<NearByRestaurantsApiService>()
    lateinit var apiService: NearByRestaurantsApiService

    @Before
    fun setUp() {
        apiService = NearByRestaurantsApiService.create()
    }

    @Test
    fun testNearByRestaurants_200OK_response() {
        whenever(apiService.nearByRestaurantSearchQuery(any(), any(), any(), any())).thenReturn(
            Observable.just(
                nearByRestaurantsList()
            )
        )

        val subscriber = TestSubscriber<List<RestaurantsNearBy>>()
        apiService.nearByRestaurantSearchQuery(any(), any(), any(), any()).subscribe(subscriber)

        subscriber.awaitTerminalEvent()
        subscriber.assertNoErrors()
    }

    private fun nearByRestaurantsList(): List<RestaurantsNearBy> {
        return listOf(
            sampleRestaurants("Hotel 1", "Fremont1", "43 wait"),
            sampleRestaurants("Hotel 2", "Fremont2", "42 wait"),
            sampleRestaurants("Hotel 3", "Fremont3", "41 wait")
        )
    }

    private fun sampleRestaurants(name: String, description: String, waitTime: String): RestaurantsNearBy {
        return RestaurantsNearBy(any(), any(), any(), description, any(), name, waitTime)
    }
}