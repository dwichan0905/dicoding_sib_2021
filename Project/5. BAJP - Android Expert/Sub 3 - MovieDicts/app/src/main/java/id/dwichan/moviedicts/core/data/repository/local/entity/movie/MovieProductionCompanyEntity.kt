package id.dwichan.moviedicts.core.data.repository.local.entity.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_prod_company")
data class MovieProductionCompanyEntity(

    @ColumnInfo(name = "logo_path")
    var logoPath: String? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @PrimaryKey
    @ColumnInfo(name = "prod_id")
    var id: Int? = null
)