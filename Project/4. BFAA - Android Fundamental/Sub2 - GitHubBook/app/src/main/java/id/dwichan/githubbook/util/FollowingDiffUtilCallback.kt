package id.dwichan.githubbook.util

import androidx.recyclerview.widget.DiffUtil
import id.dwichan.githubbook.data.network.response.UserItem

class FollowingDiffUtilCallback(
        private val oldList: List<UserItem>,
        private val newList: List<UserItem>
    ) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItemPosition == newItemPosition

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItemPosition == newItemPosition

}