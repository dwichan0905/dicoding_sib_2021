package id.dwichan.moviedicts.core.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreatedByDataEntity(
    val gender: Int? = null,
    val creditId: String? = null,
    val name: String? = null,
    val profilePath: String? = null,
    val id: Int? = null
) : Parcelable