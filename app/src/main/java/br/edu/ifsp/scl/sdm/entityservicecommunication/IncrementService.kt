package br.edu.ifsp.scl.sdm.entityservicecommunication

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.os.Looper
import android.os.Message

class IncrementService : Service() {
    private inner class IncrementHandler(looper: Looper): Handler(looper) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            msg.data.getInt("VALUE").also {
                Intent("INCREMENT_VALUE_ACTION").apply {
                    putExtra("VALUE", it + 1)
                    sendBroadcast(this)
                }
            }
            stopSelf()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.getIntExtra("VALUE", -1)?.also { value ->
            HandlerThread("IncrementThread").apply {
                start()
                IncrementHandler(looper).apply {
                    obtainMessage().apply {
                        data.putInt("VALUE", value)
                        sendMessage(this)
                    }
                }
            }
        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? = null;
}