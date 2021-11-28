package id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.output

import androidx.room.ColumnInfo

data class TelevisionWithProductionCompanyOut(
    @ColumnInfo(name = "logo_path")
    var logoPath: String? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "prod_id")
    var id: Int? = null
)