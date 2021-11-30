package id.dwichan.moviedicts.core.util.bookmark

import androidx.recyclerview.widget.DiffUtil
import id.dwichan.moviedicts.core.data.entity.MovieTelevisionDataEntity

class BookmarkDiffUtilCallback(
    private val oldList: List<MovieTelevisionDataEntity>,
    private val newList: List<MovieTelevisionDataEntity>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItemPosition == newItemPosition

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}