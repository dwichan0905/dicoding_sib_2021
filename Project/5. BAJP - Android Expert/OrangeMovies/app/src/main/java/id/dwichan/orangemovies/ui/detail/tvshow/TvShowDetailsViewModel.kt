package id.dwichan.orangemovies.ui.detail.tvshow

import androidx.lifecycle.ViewModel
import id.dwichan.orangemovies.data.Crew
import id.dwichan.orangemovies.data.TvShow
import id.dwichan.orangemovies.utility.DataDummy

class TvShowDetailsViewModel : ViewModel() {

    private lateinit var tvId: String

    fun setTvShowId(tvId: String) {
        this.tvId = tvId
    }

    fun getTvShowData(): TvShow = DataDummy.getTvShowData(tvId)!!

    fun getTvShowCrews(): ArrayList<Crew> = DataDummy.getTvShowCrews(tvId)
}