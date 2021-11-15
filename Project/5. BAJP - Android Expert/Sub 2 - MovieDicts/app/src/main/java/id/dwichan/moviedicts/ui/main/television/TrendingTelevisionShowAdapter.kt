package id.dwichan.moviedicts.ui.main.television

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.dwichan.moviedicts.R
import id.dwichan.moviedicts.core.data.repository.remote.response.trending.TrendingResultsItem
import id.dwichan.moviedicts.core.util.TrendingResultsItemDiffUtilCallback
import id.dwichan.moviedicts.databinding.ItemTvShowsTrendingBinding

class TrendingTelevisionShowAdapter :
    RecyclerView.Adapter<TrendingTelevisionShowAdapter.TrendingTelevisionShowViewHolder>() {

    private var data: ArrayList<TrendingResultsItem> = ArrayList()
    var itemAction: OnItemActionListener? = null

    inner class TrendingTelevisionShowViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val binding = ItemTvShowsTrendingBinding.bind(itemView)

        fun bind(position: Int, item: TrendingResultsItem) {
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
                val score = "\uD83D\uDC4D ${item.voteAverage}"
                textMovieScore.text = score

                root.setOnClickListener {
                    itemAction?.onItemClick(position, binding, item)
                }
            }
        }
    }

    fun setItems(list: List<TrendingResultsItem>) {
        val callback = TrendingResultsItemDiffUtilCallback(this.data, list)
        val diffUtil = DiffUtil.calculateDiff(callback)
        this.data.clear()
        this.data.addAll(list)
        diffUtil.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrendingTelevisionShowAdapter.TrendingTelevisionShowViewHolder {
        val binding = ItemTvShowsTrendingBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendingTelevisionShowViewHolder(binding.root)
    }

    override fun onBindViewHolder(
        holder: TrendingTelevisionShowAdapter.TrendingTelevisionShowViewHolder,
        position: Int
    ) {
        holder.bind(position, data[position])
    }

    override fun getItemCount(): Int = data.size

    interface OnItemActionListener {
        fun onItemClick(
            position: Int,
            itemBind: ItemTvShowsTrendingBinding,
            item: TrendingResultsItem
        )
    }
}