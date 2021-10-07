package id.dwichan.githubbook.ui.detail.content.repository

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.dwichan.githubbook.data.entity.Repository
import id.dwichan.githubbook.databinding.ItemRepositoryBinding
import id.dwichan.githubbook.ui.detail.content.follower.FollowerDiffUtilCallback

class RepositoryAdapter(private val username: String):
    RecyclerView.Adapter<RepositoryAdapter.ReposViewHolder>() {

    private var repoList: ArrayList<Repository> = ArrayList()

    inner class ReposViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val binding = ItemRepositoryBinding.bind(itemView)

        fun bind(repository: Repository) {
            binding.apply {
                textRepoName.text = repository.name
                textRepoPath.text = repository.fullName

                root.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("https://github.com/$username/${repository.name}")
                    root.context.startActivity(intent)
                }
            }
        }
    }

    fun updateRepositoryList(newSource: List<Repository>) {
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