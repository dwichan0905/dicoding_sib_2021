package id.dwichan.moviedicts.core.data.repository.local.entity.tvshow

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "television_prod_company")
data class TelevisionProductionCompanyEntity(
    @ColumnInfo(name = "logo_path")
    var logoPath: String? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @PrimaryKey
    @ColumnInfo(name = "prod_id")
    var id: Int? = null
)