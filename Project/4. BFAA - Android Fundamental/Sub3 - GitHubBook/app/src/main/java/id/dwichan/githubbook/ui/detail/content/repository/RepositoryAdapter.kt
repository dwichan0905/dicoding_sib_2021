package id.dwichan.githubbook.ui.detail.content.repository

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.dwichan.githubbook.data.repository.network.response.RepositoryItem
import id.dwichan.githubbook.databinding.ItemRepositoryBinding
import id.dwichan.githubbook.util.RepositoryDiffUtilCallback

class RepositoryAdapter :
    RecyclerView.Adapter<RepositoryAdapter.ReposViewHolder>() {

    private var repoList: ArrayList<RepositoryItem> = ArrayList()

    inner class ReposViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemRepositoryBinding.bind(itemView)

        fun bind(repository: RepositoryItem) {
            binding.apply {
                textRepoName.text = repository.name
                textRepoPath.text = repository.fullName

                root.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(repository.htmlUrl)
                    root.context.startActivity(intent)
                }
            }
        }
    }

    fun setRepositoryList(newSource: List<RepositoryItem>) {
        val diffUtilCallback = RepositoryDiffUtilCallback(repoList, newSource)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        repoList.clear()
        repoList.addAll(newSource)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val view = ItemRepositoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ReposViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.bind(repoList[position])
    }

    override fun getItemCount(): Int = repoList.size
}