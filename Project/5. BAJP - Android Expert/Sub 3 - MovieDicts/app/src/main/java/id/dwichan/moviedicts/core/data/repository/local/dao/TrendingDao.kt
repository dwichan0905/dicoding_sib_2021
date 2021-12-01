package id.dwichan.moviedicts.core.data.repository.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.dwichan.moviedicts.core.data.repository.local.entity.TrendingEntity
import id.dwichan.moviedicts.vo.Type

@Dao
abstract class TrendingDao {

    @Query(
        """
        SELECT * FROM trending
        WHERE media_type = '${Type.MEDIA_TYPE_MOVIES}'
        AND interval = '${Type.INTERVAL_TODAY}'
        ORDER BY timestamp DESC
    """
    )
    abstract fun getTrendingMoviesToday(): DataSource.Factory<Int, TrendingEntity>

    @Query(
        """
        SELECT * FROM trending
        WHERE media_type = '${Type.MEDIA_TYPE_MOVIES}'
        AND interval = '${Type.INTERVAL_WEEKLY}'
        ORDER BY timestamp DESC
    """
    )
    abstract fun getTrendingMoviesWeekly(): DataSource.Factory<Int, TrendingEntity>

    @Query(
        """
        SELECT * FROM trending
        WHERE media_type = '${Type.MEDIA_TYPE_TELEVISION}'
        AND interval = '${Type.INTERVAL_TODAY}'
        ORDER BY timestamp DESC
    """
    )
    abstract fun getTrendingTelevisionShowToday(): DataSource.Factory<Int, TrendingEntity>

    @Query(
        """
        SELECT * FROM trending
        WHERE media_type = '${Type.MEDIA_TYPE_TELEVISION}'
        AND interval = '${Type.INTERVAL_WEEKLY}'
        ORDER BY timestamp DESC
    """
    )
    abstract fun getTrendingTelevisionShowWeekly(): DataSource.Factory<Int, TrendingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTrendingEntity(trendingEntity: TrendingEntity)

    fun insertWithTimestamp(trendingEntity: TrendingEntity) {
        insertTrendingEntity(trendingEntity.apply {
            _timestamp = System.currentTimeMillis()
        })
    }
}