package id.dwichan.githubbook.ui.main

import androidx.recyclerview.widget.DiffUtil
import id.dwichan.githubbook.data.network.response.UserItem

class UserDiffUtilCallback(
    private val oldList: List<UserItem>,
    private val newList: List<UserItem>
    ) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItemPosition == newItemPosition

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItemPosition == newItemPosition

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}