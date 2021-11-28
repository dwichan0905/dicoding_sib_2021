package id.dwichan.moviedicts.core.data.repository.local.entity.movie.helpers

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "movie_with_production_company", primaryKeys = ["movie_id", "prod_id"])
data class MovieProductionCompanyCrossRef(
    @ColumnInfo(name = "movie_id")
    @NonNull
    var movieId: Int,

    @ColumnInfo(name = "prod_id")
    @NonNull
    var productionId: Int
)
