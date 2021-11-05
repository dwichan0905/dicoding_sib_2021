package id.dwichan.orangemovies.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import id.dwichan.orangemovies.R
import id.dwichan.orangemovies.utility.DataDummy
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val dummyMovie = DataDummy.generateMovies()
    private val dummyHighlight = DataDummy.getHighlightMovies()
    private val dummyTvShow = DataDummy.generateTvShows()

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadHighlightMovies() {
        onView(withId(R.id.rec_highlight_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rec_highlight_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyHighlight.size))
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rec_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rec_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadTvShow() {
        onView(withId(R.id.nav_tv_shows)).perform(click())
        onView(withId(R.id.rec_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rec_tv_shows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun loadHighlightMovieDetails() {
        onView(withId(R.id.rec_highlight_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.tv_movie_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_name)).check(matches(withText(dummyHighlight[0].title)))

        onView(withId(R.id.tv_movie_certification)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_certification)).check(matches(withText(dummyHighlight[0].certification)))

        onView(withId(R.id.tv_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date)).check(matches(withText(dummyHighlight[0].releaseDate)))

        onView(withId(R.id.tv_user_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_user_score)).check(matches(withText("${dummyHighlight[0].userScore}%")))

        onView(withId(R.id.tv_synopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_synopsis)).check(matches(withText(dummyHighlight[0].synopsis)))
    }

    @Test
    fun loadMovieDetails() {
        onView(withId(R.id.rec_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.tv_movie_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_name)).check(matches(withText(dummyMovie[0].title)))

        onView(withId(R.id.tv_movie_certification)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_certification)).check(matches(withText(dummyMovie[0].certification)))

        onView(withId(R.id.tv_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date)).check(matches(withText(dummyMovie[0].releaseDate)))

        onView(withId(R.id.tv_user_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_user_score)).check(matches(withText("${dummyMovie[0].userScore}%")))

        onView(withId(R.id.tv_synopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_synopsis)).check(matches(withText(dummyMovie[0].synopsis)))
    }

    @Test
    fun loadTvShowDetails() {
        onView(withId(R.id.nav_tv_shows)).perform(click())
        onView(withId(R.id.rec_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.tv_tv_show_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tv_show_name)).check(matches(withText(dummyTvShow[0].title)))

        onView(withId(R.id.tv_tv_show_certification)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tv_show_certification)).check(matches(withText(dummyTvShow[0].certification)))

        onView(withId(R.id.tv_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_year)).check(matches(withText(dummyTvShow[0].year.toString())))

        onView(withId(R.id.tv_user_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_user_score)).check(matches(withText("${dummyTvShow[0].userScore}%")))

        onView(withId(R.id.tv_synopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_synopsis)).check(matches(withText(dummyTvShow[0].synopsis)))
    }
}