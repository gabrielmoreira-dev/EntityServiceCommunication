package br.edu.ifsp.scl.sdm.entityservicecommunication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.sdm.entityservicecommunication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var incrementServiceIntent: Intent
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
        incrementServiceIntent = Intent(this, IncrementService::class.java)
        with (amb) {
            mainTb.apply {
                getString(R.string.app_name).also { setTitle(it) }
                setSupportActionBar(this)
            }
            incrementBt.setOnClickListener {
                startService(incrementServiceIntent.apply {
                    putExtra("VALUE", counter)
                })
            }
        }
        InterEntityCommunication.value.observe(this) {
            counter = it
            Toast.makeText(
                this,
                "You clicked $counter times",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(incrementServiceIntent)
    }
}