package id.dwichan.githubbook.ui.main.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.dwichan.githubbook.R
import id.dwichan.githubbook.data.repository.local.entity.FavoriteUser
import id.dwichan.githubbook.databinding.ItemUsersBinding
import id.dwichan.githubbook.util.FavoriteUserDiffUtilCallback

class FavoriteUsersAdapter : RecyclerView.Adapter<FavoriteUsersAdapter.UsersViewHolder>() {

    private var users: ArrayList<FavoriteUser> = ArrayList()
    var onItemAction: OnItemActionListener? = null

    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding: ItemUsersBinding = ItemUsersBinding.bind(itemView)

        fun bind(item: FavoriteUser) {
            with(binding) {
                val context = binding.root.context

                Glide.with(context)
                    .load(item.avatarUrl)
                    .placeholder(R.drawable.ic_baseline_hourglass_bottom_24)
                    .error(R.drawable.ic_baseline_error_24)
                    .into(imageUser)

                textUserName.text = item.login
                textUserDetails.text = item.type

                root.setOnClickListener {
                    onItemAction?.onClick(item, binding)
                }
            }
        }
    }

    fun setUserList(users: List<FavoriteUser>) {
        val callback = FavoriteUserDiffUtilCallback(this.users, users)
        val diffUtil = DiffUtil.calculateDiff(callback)
        this.users.clear()
        this.users.addAll(users)
        diffUtil.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersViewHolder {
        val view = ItemUsersBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UsersViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    interface OnItemActionListener {
        fun onClick(item: FavoriteUser, itemBinding: ItemUsersBinding)
    }
}