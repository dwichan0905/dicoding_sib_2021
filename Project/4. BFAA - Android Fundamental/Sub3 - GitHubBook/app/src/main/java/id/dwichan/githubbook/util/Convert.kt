package id.dwichan.githubbook.util

import android.content.Context
import android.util.TypedValue

object Convert {
    fun dpToPx(context: Context, dp: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.resources.displayMetrics
        )
    }
}