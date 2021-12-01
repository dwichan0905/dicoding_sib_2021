package id.dwichan.moviedicts.ui.main.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.dwichan.moviedicts.R
import id.dwichan.moviedicts.core.data.entity.TrendingResultsDataEntity
import id.dwichan.moviedicts.core.util.TrendingResultsItemDiffUtilCallback
import id.dwichan.moviedicts.databinding.ItemMoviesTrendingBinding

class TrendingMoviesAdapter :
    PagedListAdapter<TrendingResultsDataEntity, TrendingMoviesAdapter.TrendingMoviesViewHolder>(
        diffCallback
    ) {

    var itemAction: OnItemActionListener? = null

    inner class TrendingMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemMoviesTrendingBinding.bind(itemView)

        fun bind(position: Int, item: TrendingResultsDataEntity) {
            binding.apply {
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/original${item.posterPath}")
                    .placeholder(R.drawable.ic_loading_image)
                    .error(R.drawable.ic_error_image)
                    .into(imageMoviePoster)

                val movieName =
                    item.title ?: item.originalTitle ?: item.name ?: item.originalName
                    ?: "Unknown"
                textMovieName.text = movieName
                if (item.adult == true) {
                    textMovieAdultStatus.visibility = View.VISIBLE
                } else {
                    textMovieAdultStatus.visibility = View.GONE
                }
                val score = "\uD83D\uDC4D ${item.voteAverage}"
                textMovieScore.text = score

                root.setOnClickListener {
                    itemAction?.onItemClick(position, binding, item)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrendingMoviesAdapter.TrendingMoviesViewHolder {
        val binding = ItemMoviesTrendingBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendingMoviesViewHolder(binding.root)
    }

    override fun onBindViewHolder(
        holder: TrendingMoviesAdapter.TrendingMoviesViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(position, item)
        }
    }

    interface OnItemActionListener {
        fun onItemClick(
            position: Int,
            itemBind: ItemMoviesTrendingBinding,
            item: TrendingResultsDataEntity
        )
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<TrendingResultsDataEntity>() {
            override fun areItemsTheSame(
                oldItem: TrendingResultsDataEntity,
                newItem: TrendingResultsDataEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TrendingResultsDataEntity,
                newItem: TrendingResultsDataEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}