package id.dwichan.moviedicts.core.util

import androidx.recyclerview.widget.DiffUtil
import id.dwichan.moviedicts.core.data.entity.TrendingResultsDataEntity

class TrendingResultsItemDiffUtilCallback(
    private val oldList: List<TrendingResultsDataEntity>,
    private val newList: List<TrendingResultsDataEntity>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItemPosition == newItemPosition

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}