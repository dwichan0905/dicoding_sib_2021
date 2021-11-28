package id.dwichan.moviedicts.core.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenresDataEntity(
    val name: String? = null,
    val id: Int? = null
) : Parcelable