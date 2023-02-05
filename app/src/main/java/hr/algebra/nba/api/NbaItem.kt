package hr.algebra.nba.api

import com.google.gson.annotations.SerializedName

data class NbaItem(
    @SerializedName("first_name") val first_name : String,
    @SerializedName("height_inches") val height_inches : Double,
    @SerializedName("last_name") val last_name : String,
    @SerializedName("position") val position : String,
    @SerializedName("weight_pounds") val weight_pounds : Double
)
