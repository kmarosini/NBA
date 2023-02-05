package hr.algebra.nba.model

import com.google.gson.annotations.SerializedName

data class ItemDao(
    @SerializedName("data" ) var data : ArrayList<Player> = arrayListOf()
)
