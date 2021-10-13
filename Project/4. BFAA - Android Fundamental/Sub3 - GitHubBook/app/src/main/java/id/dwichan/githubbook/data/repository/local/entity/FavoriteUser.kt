package id.dwichan.githubbook.data.repository.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorite_user")
@Parcelize
data class FavoriteUser(

    @ColumnInfo(name = "login")
    @PrimaryKey
    var login: String,

    @ColumnInfo(name = "type")
    var type: String,

    @ColumnInfo(name = "avatarUrl")
    var avatarUrl: String,

    @ColumnInfo(name = "dateAdded")
    var dateAdded: String

) : Parcelable