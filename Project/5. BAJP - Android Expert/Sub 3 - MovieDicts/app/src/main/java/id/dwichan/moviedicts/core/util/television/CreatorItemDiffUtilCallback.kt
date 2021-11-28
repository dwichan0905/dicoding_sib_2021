package id.dwichan.moviedicts.core.util.television

import androidx.recyclerview.widget.DiffUtil
import id.dwichan.moviedicts.core.data.entity.CreatedByDataEntity

class CreatorItemDiffUtilCallback(
    private val oldList: List<CreatedByDataEntity>,
    private val newList: List<CreatedByDataEntity>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItemPosition == newItemPosition

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItemPosition == newItemPosition
}