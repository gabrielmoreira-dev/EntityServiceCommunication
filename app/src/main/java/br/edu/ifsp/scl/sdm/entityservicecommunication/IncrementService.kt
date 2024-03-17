package br.edu.ifsp.scl.sdm.entityservicecommunication

import android.app.Service
import android.content.Intent
import android.os.IBinder

class IncrementService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.getIntExtra("VALUE", -1)?.also {
            InterEntityCommunication.value.postValue(it + 1)
        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? = null;
}