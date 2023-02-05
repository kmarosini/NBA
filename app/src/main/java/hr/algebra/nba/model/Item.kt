package hr.algebra.nba.model

import com.google.gson.annotations.SerializedName
import java.security.cert.CertPath

data class Item(
    var _id: Long?,
    val first_name : String,
    val height_inches : Double,
    val last_name : String,
    val position : String,
    val weight_pounds : Double,
    var read: Boolean
)
