package id.dwichan.orangemovies.ui.main.tvshow

import androidx.lifecycle.ViewModel
import id.dwichan.orangemovies.data.TvShow
import id.dwichan.orangemovies.utility.DataDummy

class TvShowViewModel : ViewModel() {
    fun getTvShows(): ArrayList<TvShow> = DataDummy.generateTvShows()
}