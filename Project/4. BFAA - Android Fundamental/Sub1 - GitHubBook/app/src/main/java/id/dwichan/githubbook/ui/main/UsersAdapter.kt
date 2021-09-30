package id.dwichan.githubbook.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.dwichan.githubbook.R
import id.dwichan.githubbook.data.UserItem
import id.dwichan.githubbook.databinding.ItemUsersBinding

class UsersAdapter(private val users: List<UserItem>) :
    RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    var onItemAction: OnItemActionListener? = null

    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding: ItemUsersBinding = ItemUsersBinding.bind(itemView)

        fun bind(item: UserItem) {
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

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent, false)
        return UsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    interface OnItemActionListener {
        fun onClick(item: UserItem, itemBinding: ItemUsersBinding)
    }
}