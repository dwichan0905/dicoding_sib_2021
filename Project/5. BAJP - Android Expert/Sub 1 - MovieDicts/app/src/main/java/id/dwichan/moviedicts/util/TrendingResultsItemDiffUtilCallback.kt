package id.dwichan.moviedicts.util

import androidx.recyclerview.widget.DiffUtil
import id.dwichan.moviedicts.data.repository.remote.response.trending.TrendingResultsItem

class TrendingResultsItemDiffUtilCallback(
    private val oldList: List<TrendingResultsItem>,
    private val newList: List<TrendingResultsItem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItemPosition == newItemPosition

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItemPosition == newItemPosition
}