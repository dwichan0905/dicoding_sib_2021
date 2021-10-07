package id.dwichan.githubbook.ui.animationdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.widget.Toast
import id.dwichan.githubbook.databinding.ActivityAnimationDialogBinding

class AnimationDialogActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TYPE = "extra_type"
        const val EXTRA_MESSAGE = "extra_message"

        const val TYPE_FAVORITE_ADD = 0x00000001
        const val TYPE_FAVORITE_REMOVE = 0x00000002

        private const val TIME_TO_FINISH_IN_MILLIS = 2000L
    }

    private var _binding: ActivityAnimationDialogBinding? = null
    private val binding get() = _binding!!

    private var type = 0x0000000
    private var message = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        _binding = ActivityAnimationDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFinishOnTouchOutside(false)

        val bundle = intent.extras
        if (bundle != null) {
            bundle.apply {
                type = getInt(EXTRA_TYPE)
                message = getString(EXTRA_MESSAGE).toString()
            }
            processDialog()
        } else {
            Toast.makeText(this, "Failed to get type and message!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun processDialog() {
        when (type) {
            TYPE_FAVORITE_ADD -> binding.animFavoriteOn.visibility = View.VISIBLE
            TYPE_FAVORITE_REMOVE -> binding.animFavoriteOff.visibility = View.VISIBLE
            else -> return
        }

        binding.textMessage.text = message

        Handler(Looper.getMainLooper()).postDelayed({
            finish()
        }, TIME_TO_FINISH_IN_MILLIS)
    }

    override fun onBackPressed() {
        return
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}