package id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.output

import androidx.room.ColumnInfo

data class TelevisionWithCreatedByOut(
    @ColumnInfo(name = "gender")
    var gender: Int? = null,

    @ColumnInfo(name = "credit_id")
    var creditId: String? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "profile_path")
    var profilePath: String? = null,

    @ColumnInfo(name = "creator_id")
    var id: Int? = null
)
