package hr.algebra.nba.framework

import android.annotation.SuppressLint
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.content.getSystemService
import androidx.preference.PreferenceManager
import hr.algebra.nba.HostActivity
import hr.algebra.nba.NASA_PROVIDER_CONTENT_URI
import hr.algebra.nba.model.Player

fun View.applyAnimation(animationId: Int)
    = startAnimation(AnimationUtils.loadAnimation(context, animationId))

inline fun<reified T: Activity> Context.startActivity() =
    startActivity(Intent(this, T::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))

inline fun<reified T: Activity> Context.startActivity(key: String, value: Int)
        = startActivity(
    Intent(this, T::class.java).apply {
        putExtra(key, value)
    }.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))

inline fun<reified T: BroadcastReceiver> Context.sendBroadcast()
    = sendBroadcast(Intent(this, T::class.java))


fun Context.setBooleanPreference(key: String, value: Boolean = true) {
    PreferenceManager.getDefaultSharedPreferences(this)
        .edit()
        .putBoolean(key, value)
        .apply()
}


fun Context.getBooleanPreference(key: String) =
    PreferenceManager.getDefaultSharedPreferences(this)
        .getBoolean(key, false)


fun Context.isOnline() :Boolean {
    val connectivityManager = getSystemService<ConnectivityManager>()
    connectivityManager?.activeNetwork?.let { network ->
        connectivityManager.getNetworkCapabilities(network)?.let { cap ->
            return cap.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || cap.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        }
    }
    return false
}


fun callDelayed(delay: Long, runnable: Runnable) {
    Handler(Looper.getMainLooper()).postDelayed(
        runnable,
        delay
    )



}

@SuppressLint("Range")
fun Context.fetchItems() : MutableList<Player> {
    val items = mutableListOf<Player>()
    val cursor = contentResolver.query(NASA_PROVIDER_CONTENT_URI,
        null, null, null, null)
    while (cursor != null && cursor.moveToNext()) {
        items.add(Player(
            cursor.getInt(cursor.getColumnIndex(Player::id.name)),
            cursor.getString(cursor.getColumnIndex(Player::firstName.name)),
            cursor.getString(cursor.getColumnIndex(Player::lastName.name)),
            cursor.getString(cursor.getColumnIndex(Player::heightFeet.name)),
            cursor.getString(cursor.getColumnIndex(Player::weightPounds.name)),
            cursor.getString(cursor.getColumnIndex(Player::heightInches.name))
        ))
    }

    return items
}