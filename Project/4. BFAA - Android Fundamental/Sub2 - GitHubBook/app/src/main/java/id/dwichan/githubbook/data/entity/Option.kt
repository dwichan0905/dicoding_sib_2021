package id.dwichan.githubbook.data.entity

import android.view.View
import androidx.annotation.DrawableRes

data class Option(
    @DrawableRes var icon: Int = 0,
    var title: String = "",
    var showNextIndicator: Boolean = true,
    var onClick: View.OnClickListener
)
