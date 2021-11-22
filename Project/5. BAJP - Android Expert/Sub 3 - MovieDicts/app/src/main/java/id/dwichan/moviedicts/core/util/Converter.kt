package id.dwichan.moviedicts.core.util

import id.dwichan.moviedicts.core.data.repository.remote.response.movie.MovieGenresItem
import id.dwichan.moviedicts.core.data.repository.remote.response.television.TelevisionShowGenresItem
import java.text.SimpleDateFormat
import java.util.*

object Converter {
    object Date {
        fun reformatDateString(dateString: String): String {
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateString)
            return SimpleDateFormat("MMMM d, yyyy", Locale.getDefault()).format(date!!)
        }
    }

    object Duration {
        fun minutesToString(minutes: Long): String {
            val hour = (minutes / 60).toInt()
            val minute = (minutes % 60).toInt()

            val builder = StringBuilder()
            if (hour > 0) {
                builder.append(hour).append("h")
            }
            if (minute != 0) {
                if (minute > 0) {
                    builder.append(" ").append(minute).append("m")
                } else {
                    builder.append(minutes).append("m")
                }
            }

            return builder.toString()
        }
    }

    object Movies {
        fun listGenresToStringList(genres: List<MovieGenresItem>): String {
            var genre = ""
            for (items in genres.indices) {
                genre += if (items < (genres.size - 1)) {
                    genres[items].name + ", "
                } else {
                    genres[items].name
                }
            }
            return genre
        }
    }

    object TelevisionShow {
        fun listGenresToStringList(genres: List<TelevisionShowGenresItem>): String {
            var genre = ""
            for (items in genres.indices) {
                genre += if (items < (genres.size - 1)) {
                    genres[items].name + ", "
                } else {
                    genres[items].name
                }
            }
            return genre
        }
    }
}