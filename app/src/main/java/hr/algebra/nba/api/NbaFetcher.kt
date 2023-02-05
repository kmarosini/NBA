package hr.algebra.nba.api

import android.content.ContentValues
import android.content.Context
import android.util.Log
import hr.algebra.nba.NASA_PROVIDER_CONTENT_URI
import hr.algebra.nba.NbaReciever
import hr.algebra.nba.framework.sendBroadcast
import hr.algebra.nba.model.ItemDao
import hr.algebra.nba.model.Player
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NbaFetcher(private val context: Context) {
    private var nbaApi: NbaApi
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        nbaApi = retrofit.create(NbaApi::class.java)
    }

    fun fetchItems(count: Int) {

         val request =nbaApi.fetchItems(count)

        request.enqueue(object: Callback<ItemDao> {
            override fun onResponse(call: Call<ItemDao>, response: Response<ItemDao>) {
                response?.body()?.let {
                    populateItems(it)
                }
            }

            override fun onFailure(call: Call<ItemDao>, t: Throwable) {
                Log.e(javaClass.name, t.toString(), t)
            }


        })
    }

    private fun populateItems(nbaItems: ItemDao) {
        val items = mutableListOf<Player>()
        GlobalScope.launch {
            println(nbaItems)
            nbaItems.data.forEach {
                val values = ContentValues().apply {
                    put(Player::firstName.name, it.firstName)
                    put(Player::lastName.name, it.lastName)
                    put(Player::heightFeet.name, it.heightFeet)
                    put(Player::heightInches.name, it.heightInches)
                    put(Player::weightPounds.name, it.weightPounds)

                }

                context.contentResolver.insert(NASA_PROVIDER_CONTENT_URI, values)

            }
        }


        context.sendBroadcast<NbaReciever>()

    }
}