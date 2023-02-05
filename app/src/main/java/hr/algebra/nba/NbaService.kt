package hr.algebra.nba

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import hr.algebra.nba.api.NbaFetcher
import hr.algebra.nba.framework.sendBroadcast

private const val JOB_ID = 1
class NbaService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        NbaFetcher(this).fetchItems(10)
        sendBroadcast<NbaReciever>()

    }

    companion object {
        fun enqueue(context: Context) {
            enqueueWork(context, NbaService::class.java, JOB_ID,
                            Intent(context, NbaService::class.java))
        }
    }
}