package id.dwichan.moviedicts.core.data.repository.local.entity.movie.output

import androidx.room.ColumnInfo

data class MovieWithProdCompanyOut(
    @ColumnInfo(name = "logo_path")
    var logoPath: String? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "prod_id")
    var id: Int? = null
)