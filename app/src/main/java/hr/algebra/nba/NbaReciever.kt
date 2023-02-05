package hr.algebra.nba

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hr.algebra.nba.framework.setBooleanPreference
import hr.algebra.nba.framework.startActivity

class NbaReciever : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.setBooleanPreference(DATA_IMPORTED)
        context.startActivity<HostActivity>()
    }
}