package id.dwichan.moviedicts.ui.main.bookmark.television

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.dwichan.moviedicts.R
import id.dwichan.moviedicts.core.data.entity.MovieTelevisionDataEntity
import id.dwichan.moviedicts.core.util.bookmark.BookmarkDiffUtilCallback
import id.dwichan.moviedicts.databinding.ItemBookmarkBinding

class BookmarkTelevisionAdapter: RecyclerView.Adapter<BookmarkTelevisionAdapter.BookmarkViewHolder>() {

    private val bookmarkItem = ArrayList<MovieTelevisionDataEntity>()
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

    fun setBookmarkList(bookmarks: List<MovieTelevisionDataEntity>) {
        val callback = BookmarkDiffUtilCallback(bookmarkItem, bookmarks)
        val diffUtil = DiffUtil.calculateDiff(callback)
        this.bookmarkItem.clear()
        this.bookmarkItem.addAll(bookmarks)
        diffUtil.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val binding = ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.bind(position, bookmarkItem[position])
    }

    override fun getItemCount(): Int = bookmarkItem.size

    interface OnItemActionListener {
        fun onItemClick(
            position: Int,
            itemBind: ItemBookmarkBinding,
            item: MovieTelevisionDataEntity
        )
    }
}