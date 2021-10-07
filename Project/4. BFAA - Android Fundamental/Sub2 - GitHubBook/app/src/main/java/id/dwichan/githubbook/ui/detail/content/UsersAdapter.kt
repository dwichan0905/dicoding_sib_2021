package id.dwichan.githubbook.ui.detail.content

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.dwichan.githubbook.R
import id.dwichan.githubbook.data.entity.User
import id.dwichan.githubbook.databinding.ItemUsersBinding

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {
    private var users: ArrayList<User> = ArrayList()
    var onItemAction: OnItemActionListener? = null

    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding: ItemUsersBinding = ItemUsersBinding.bind(itemView)

        fun bind(item: User) {
            with(binding) {
                val context = binding.root.context

                Glide.with(context)
                    .load(context.resources.getIdentifier(item.avatar, null, context.packageName))
                    .placeholder(R.drawable.ic_baseline_hourglass_bottom_24)
                    .error(R.drawable.ic_baseline_error_24)
                    .into(imageUser)

                textUserName.text = item.name
                var userDetails = item.username
                if (item.company != "null") userDetails += " | ${item.company}"
                textUserDetails.text = userDetails

                root.setOnClickListener {
                    onItemAction?.onClick(item, binding)
                }
            }
        }
    }

    fun setUserList(users: List<User>) {

        this.users.clear()
        this.users.addAll(users)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_follower_following,
            parent,
            false
        )
        return UsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    interface OnItemActionListener {
        fun onClick(item: User, itemBinding: ItemUsersBinding)
    }
}