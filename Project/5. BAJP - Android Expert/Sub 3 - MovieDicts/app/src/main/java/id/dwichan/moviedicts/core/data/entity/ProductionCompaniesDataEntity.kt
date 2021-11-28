package id.dwichan.moviedicts.core.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductionCompaniesDataEntity(
    val logoPath: String? = null,
    val name: String? = null,
    val id: Int? = null
) : Parcelable
