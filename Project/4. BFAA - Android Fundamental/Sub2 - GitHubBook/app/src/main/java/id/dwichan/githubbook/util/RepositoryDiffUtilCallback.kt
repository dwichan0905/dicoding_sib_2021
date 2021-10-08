package id.dwichan.githubbook.util

import androidx.recyclerview.widget.DiffUtil
import id.dwichan.githubbook.data.network.response.RepositoryItem

class RepositoryDiffUtilCallback(
    private val oldList: List<RepositoryItem>,
    private val newList: List<RepositoryItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItemPosition == newItemPosition

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItemPosition == newItemPosition

}