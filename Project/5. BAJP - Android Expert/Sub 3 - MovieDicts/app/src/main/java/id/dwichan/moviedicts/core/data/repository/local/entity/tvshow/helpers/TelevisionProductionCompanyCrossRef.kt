package id.dwichan.moviedicts.core.data.repository.local.entity.tvshow.helpers

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "television_with_prod_company", primaryKeys = ["tv_id", "prod_id"])
data class TelevisionProductionCompanyCrossRef(
    @ColumnInfo(name = "tv_id")
    @NonNull
    var tvId: Int,

    @ColumnInfo(name = "prod_id")
    @NonNull
    var productionId: Int
)