package id.dwichan.moviedicts.ui.loading

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.dwichan.moviedicts.databinding.ActivityLoadingBinding

class LoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFinishOnTouchOutside(false)

        val broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val action = intent?.action
                if (action == INTENT_FINISH_LOADING) {
                    finish()
                }
            }
        }
        registerReceiver(broadcastReceiver, IntentFilter(INTENT_FINISH_LOADING))
    }

    companion object {
        const val INTENT_FINISH_LOADING = "intent_finish_loading"
    }

}