package id.dwichan.moviedicts.ui.main.bookmark.television

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.dwichan.moviedicts.R
import id.dwichan.moviedicts.core.data.entity.MovieTelevisionDataEntity
import id.dwichan.moviedicts.databinding.ItemBookmarkBinding

class BookmarkTelevisionAdapter :
    PagedListAdapter<MovieTelevisionDataEntity, BookmarkTelevisionAdapter.BookmarkViewHolder>(
        diffCallback
    ) {

    var itemAction: OnItemActionListener? = null

    inner class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemBookmarkBinding.bind(itemView)

        fun bind(position: Int, item: MovieTelevisionDataEntity) {
            binding.apply {
                Glide.with(root)
                    .load("https://image.tmdb.org/t/p/original${item.posterPath}")
                    .placeholder(R.drawable.ic_loading_image)
                    .error(R.drawable.ic_error_image)
                    .into(imageMoviePoster)

                textMovieName.text = item.title

                root.setOnClickListener {
                    itemAction?.onItemClick(position, binding, item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val binding =
            ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(position, item)
        }
    }

    interface OnItemActionListener {
        fun onItemClick(
            position: Int,
            itemBind: ItemBookmarkBinding,
            item: MovieTelevisionDataEntity
        )
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MovieTelevisionDataEntity>() {
            override fun areItemsTheSame(
                oldItem: MovieTelevisionDataEntity,
                newItem: MovieTelevisionDataEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MovieTelevisionDataEntity,
                newItem: MovieTelevisionDataEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}