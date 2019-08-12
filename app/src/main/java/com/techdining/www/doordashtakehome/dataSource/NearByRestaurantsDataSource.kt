package com.techdining.www.doordashtakehome.dataSource

import com.techdining.www.doordashtakehome.repository.NearByRestaurantsRepositoryProvider

class NearByRestaurantsDataSource {
    val searchResult = NearByRestaurantsRepositoryProvider.provideNearByRestaurants()
}