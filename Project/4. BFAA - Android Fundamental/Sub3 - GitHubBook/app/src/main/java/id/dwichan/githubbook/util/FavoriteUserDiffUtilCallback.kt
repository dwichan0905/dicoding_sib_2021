package id.dwichan.githubbook.util

import androidx.recyclerview.widget.DiffUtil
import id.dwichan.githubbook.data.repository.local.entity.FavoriteUser

class FavoriteUserDiffUtilCallback(
    private val oldList: List<FavoriteUser>,
    private val newList: List<FavoriteUser>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItemPosition == newItemPosition

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItemPosition == newItemPosition

}