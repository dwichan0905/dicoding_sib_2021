package id.dwichan.moviedicts.core.util.television

import androidx.recyclerview.widget.DiffUtil
import id.dwichan.moviedicts.core.data.repository.remote.response.television.CreatedByItem

class CreatorItemDiffUtilCallback(
    private val oldList: List<CreatedByItem>,
    private val newList: List<CreatedByItem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItemPosition == newItemPosition

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItemPosition == newItemPosition
}