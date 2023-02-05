package hr.algebra.nba.api

import hr.algebra.nba.model.ItemDao
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val API_URL = "https://www.balldontlie.io/api/v1/"

interface NbaApi {
    @GET("players")
    fun fetchItems(@Query("count") count : Int) : Call<ItemDao>
}