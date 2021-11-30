package id.dwichan.moviedicts.ui.main.bookmark.movies

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

class BookmarkMoviesAdapter: RecyclerView.Adapter<BookmarkMoviesAdapter.BookmarkMoviesViewHolder>() {

    private val bookmarkList = ArrayList<MovieTelevisionDataEntity>()
    var itemAction: OnItemActionListener? = null

    inner class BookmarkMoviesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

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
        val callback = BookmarkDiffUtilCallback(bookmarkList, bookmarks)
        val diffUtil = DiffUtil.calculateDiff(callback)
        this.bookmarkList.clear()
        this.bookmarkList.addAll(bookmarks)
        diffUtil.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkMoviesViewHolder {
        val binding = ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkMoviesViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: BookmarkMoviesViewHolder, position: Int) {
        holder.bind(position, bookmarkList[position])
    }

    override fun getItemCount(): Int = bookmarkList.size

    interface OnItemActionListener {
        fun onItemClick(
            position: Int,
            itemBind: ItemBookmarkBinding,
            item: MovieTelevisionDataEntity
        )
    }
}