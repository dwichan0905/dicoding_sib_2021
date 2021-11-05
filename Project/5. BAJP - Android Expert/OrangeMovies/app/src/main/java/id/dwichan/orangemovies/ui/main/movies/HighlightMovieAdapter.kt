package id.dwichan.orangemovies.ui.main.movies

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.dwichan.orangemovies.R
import id.dwichan.orangemovies.data.Movie
import id.dwichan.orangemovies.ui.detail.movie.MovieDetailsActivity
import kotlinx.android.synthetic.main.item_movies_highlight.view.*

class HighlightMovieAdapter: RecyclerView.Adapter<HighlightMovieAdapter.HighlightMovieViewHolder>() {

    private val movieData = ArrayList<Movie>()

    fun setMovies(movies: ArrayList<Movie>) {
        this.movieData.clear()
        this.movieData.addAll(movies)
        notifyDataSetChanged()
    }

    class HighlightMovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            with (itemView) {
                Glide.with(this)
                    .load(movie.poster)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_loading_24))
                    .error(R.drawable.ic_baseline_error_24)
                    .into(img_movie_poster)

                tv_movie_name.text = movie.title
                tv_movie_duration.text = movie.duration

                setOnClickListener {
                    val i = Intent(context, MovieDetailsActivity::class.java)
                    i.putExtra(MovieDetailsActivity.EXTRA_MOVIE_ID, movie.movieId)
                    (context as Activity).startActivity(i)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightMovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movies_highlight, parent, false)
        return HighlightMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: HighlightMovieViewHolder, position: Int) {
        holder.bind(movieData[position])
    }

    override fun getItemCount(): Int = movieData.size
}