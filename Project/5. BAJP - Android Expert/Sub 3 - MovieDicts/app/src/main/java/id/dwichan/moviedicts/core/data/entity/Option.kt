package id.dwichan.moviedicts.core.data.entity

import android.view.View
import androidx.annotation.DrawableRes

data class Option(
    @DrawableRes var icon: Int = 0,
    var title: String = "",
    val showNextIndicator: Boolean = true,
    var onClick: View.OnClickListener
)
