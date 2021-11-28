package id.dwichan.moviedicts.core.data.repository.local.entity.tvshow

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "television_created_by")
data class TelevisionCreatedByEntity(
    @ColumnInfo(name = "gender")
    var gender: Int? = null,

    @ColumnInfo(name = "credit_id")
    var creditId: String? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "profile_path")
    var profilePath: String? = null,

    @PrimaryKey
    @ColumnInfo(name = "creator_id")
    var id: Int? = null
)
