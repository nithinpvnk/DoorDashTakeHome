package com.techdining.www.doordashtakehome.repository

import com.techdining.www.doordashtakehome.network.NearByRestaurantsApiService

class NearByRestaurantsRepository {
    fun searchNearByRestaurants(lat: String, lng: String, offset: Int, limit: Int) = NearByRestaurantsApiService.create().nearByRestaurantSearchQuery(lat, lng,offset, limit)
}
