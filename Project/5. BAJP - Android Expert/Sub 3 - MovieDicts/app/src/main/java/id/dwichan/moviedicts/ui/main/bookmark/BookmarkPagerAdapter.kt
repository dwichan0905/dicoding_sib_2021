package id.dwichan.moviedicts.ui.main.bookmark

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.dwichan.moviedicts.R
import id.dwichan.moviedicts.ui.main.bookmark.movies.BookmarkMoviesFragment
import id.dwichan.moviedicts.ui.main.bookmark.television.BookmarkTelevisionFragment

class BookmarkPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    @StringRes
    val titles = intArrayOf(
        R.string.navigation_movie,
        R.string.navigation_television_show
    )

    override fun getItemCount(): Int = titles.size

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = BookmarkMoviesFragment()
            1 -> fragment = BookmarkTelevisionFragment()
        }
        return fragment as Fragment
    }

}