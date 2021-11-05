package id.dwichan.orangemovies.ui.main.tvshow

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.dwichan.orangemovies.R
import id.dwichan.orangemovies.data.TvShow
import id.dwichan.orangemovies.ui.detail.tvshow.TvShowDetailsActivity
import kotlinx.android.synthetic.main.item_tv_shows.view.*

class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private var tvShowData = ArrayList<TvShow>()

    fun setTvShows(tvShows: ArrayList<TvShow>) {
        this.tvShowData.clear()
        this.tvShowData.addAll(tvShows)
        notifyDataSetChanged()
    }

    class TvShowViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: TvShow) {
            with (itemView) {
                Glide.with(this)
                    .load(tvShow.poster)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_loading_24))
                    .error(R.drawable.ic_baseline_error_24)
                    .into(img_movie_poster)

                tv_tv_show_name.text = tvShow.title
                tv_certification.text = tvShow.certification

                val categoryYear = "${tvShow.category} - ${tvShow.year}"
                tv_tv_category.text = categoryYear

                setOnClickListener {
                    val i = Intent(context, TvShowDetailsActivity::class.java)
                    i.putExtra(TvShowDetailsActivity.EXTRA_TV_SHOW_ID, tvShow.tvShowId)
                    (context as Activity).startActivity(i)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tv_shows, parent, false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(tvShowData[position])
    }

    override fun getItemCount(): Int = tvShowData.size
}