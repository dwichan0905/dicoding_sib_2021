package id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.helpers

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "television_with_creator", primaryKeys = ["tv_id", "creator_id"])
data class TelevisionCreatorCrossRef(
    @ColumnInfo(name = "tv_id")
    @NonNull
    var tvId: Int,

    @ColumnInfo(name = "creator_id")
    @NonNull
    var creatorId: Int
)