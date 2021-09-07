package id.dwichan.chromebookpedia.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Chromebook(
    var image: Int = 0,
    var name: String? = null,
    var company: String? = null,
    var description: String? = null,
    var memory: String? = null,
    var storage: String? = null,
    var processor: String? = null,
    var display: String? = null,
    var graphic: String? = null,
    var color: String? = null
) : Parcelable