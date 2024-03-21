package br.edu.ifsp.scl.sdm.entityservicecommunication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class IncrementBoundService : Service() {
    private val ibsBinder: IncrementBoundServiceInterface.Stub =
        object: IncrementBoundServiceInterface.Stub() {
            override fun increment(value: Int): Int = value + 1
        }

    override fun onBind(intent: Intent): IBinder {
        Log.v(this.javaClass.simpleName, "Entity bound to the service")
        return ibsBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.v(this.javaClass.simpleName, "Entity unbound to the service")
        return super.onUnbind(intent)
    }
}