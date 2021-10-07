package id.dwichan.githubbook.ui.detail.content.repository

import androidx.recyclerview.widget.DiffUtil
import id.dwichan.githubbook.data.entity.Repository

class RepositoryDiffUtilCallback(
        private val oldList: List<Repository>,
        private val newList: List<Repository>
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