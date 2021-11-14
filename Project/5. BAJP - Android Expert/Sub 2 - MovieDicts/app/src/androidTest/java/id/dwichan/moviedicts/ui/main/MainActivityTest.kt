package id.dwichan.moviedicts.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import id.dwichan.moviedicts.R
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun shouldMoviesFragmentValid() {
        onView(withId(R.id.navigation_movies)).perform(click())
        onView(withId(R.id.fragment_movie)).check(
            matches(
                isDisplayed()
            )
        )
        onView(withId(R.id.action_bar)).check(
            matches(
                hasDescendant(
                    withText(
                        MOVIE_TITLE
                    )
                )
            )
        )
    }

    @Test
    fun shouldTelevisionShowFragmentValid() {
        onView(withId(R.id.navigation_television)).perform(click())
        onView(withId(R.id.fragment_television_show)).check(
            matches(
                isDisplayed()
            )
        )
        onView(withId(R.id.action_bar)).check(
            matches(
                hasDescendant(
                    withText(
                        TELEVISION_SHOW_TITLE
                    )
                )
            )
        )
    }

    @Test
    fun shouldBookmarkFragmentValid() {
        onView(withId(R.id.navigation_bookmark)).perform(click())
        onView(withId(R.id.fragment_bookmark)).check(
            matches(
                isDisplayed()
            )
        )
        onView(withId(R.id.action_bar)).check(
            matches(
                hasDescendant(
                    withText(
                        BOOKMARKS_TITLE
                    )
                )
            )
        )
    }

    @Test
    fun shouldAboutFragmentValid() {
        onView(withId(R.id.navigation_about)).perform(click())
        onView(withId(R.id.fragment_about)).check(
            matches(
                isDisplayed()
            )
        )
        onView(withId(R.id.action_bar)).check(
            matches(
                hasDescendant(
                    withText(
                        ABOUT_TITLE
                    )
                )
            )
        )
    }

    @Test
    fun shouldTrendingDailyMovieItemsAreValid() {
        onView(withId(R.id.navigation_movies)).perform(click())
        Thread.sleep(WAIT_TIME)
        onView(withId(R.id.rec_movies_trending_today)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                TOTAL_DATA_SHOWN
            )
        )
        onView(withId(R.id.rec_movies_trending_today)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            ),
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Thread.sleep(WAIT_TIME)

        onView(withId(R.id.image_movie_poster)).check(matches(isDisplayed()))

        onView(withId(R.id.text_movie_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_movie_title)).check(matches(not(withText(""))))

        onView(withId(R.id.text_released_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.text_released_duration)).check(matches(not(withText(""))))

        onView(withId(R.id.text_genres)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genres)).check(matches(not(withText(""))))
    }

    @Test
    fun shouldTrendingWeeklyMovieItemsAreValid() {
        onView(withId(R.id.navigation_movies)).perform(click())
        Thread.sleep(WAIT_TIME)
        onView(withId(R.id.rec_movies_trending_weekly)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                TOTAL_DATA_SHOWN
            )
        )
        onView(withId(R.id.rec_movies_trending_weekly)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            ),
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Thread.sleep(WAIT_TIME)

        onView(withId(R.id.image_movie_poster)).check(matches(isDisplayed()))

        onView(withId(R.id.text_movie_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_movie_title)).check(matches(not(withText(""))))

        onView(withId(R.id.text_released_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.text_released_duration)).check(matches(not(withText(""))))

        onView(withId(R.id.text_genres)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genres)).check(matches(not(withText(""))))
    }

    @Test
    fun shouldTrendingDailyTvShowItemsAreValid() {
        onView(withId(R.id.navigation_television)).perform(click())
        Thread.sleep(WAIT_TIME)
        onView(withId(R.id.rec_tv_trending_today)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                TOTAL_DATA_SHOWN
            )
        )
        onView(withId(R.id.rec_tv_trending_today)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            ),
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Thread.sleep(WAIT_TIME)

        onView(withId(R.id.image_tv_poster)).check(matches(isDisplayed()))

        onView(withId(R.id.text_tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_tv_title)).check(matches(not(withText(""))))

        onView(withId(R.id.text_first_air_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.text_first_air_duration)).check(matches(not(withText(""))))

        onView(withId(R.id.text_genres)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genres)).check(matches(not(withText(""))))
    }

    @Test
    fun shouldTrendingWeeklyTvShowItemsAreValid() {
        onView(withId(R.id.navigation_television)).perform(click())
        Thread.sleep(WAIT_TIME)
        onView(withId(R.id.rec_tv_trending_today)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                TOTAL_DATA_SHOWN
            )
        )
        onView(withId(R.id.rec_tv_trending_today)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            ),
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Thread.sleep(WAIT_TIME)

        onView(withId(R.id.image_tv_poster)).check(matches(isDisplayed()))

        onView(withId(R.id.text_tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_tv_title)).check(matches(not(withText(""))))

        onView(withId(R.id.text_first_air_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.text_first_air_duration)).check(matches(not(withText(""))))

        onView(withId(R.id.text_genres)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genres)).check(matches(not(withText(""))))
    }

    companion object {
        const val MOVIE_TITLE = "Movie"
        const val TELEVISION_SHOW_TITLE = "Television Show"
        const val BOOKMARKS_TITLE = "Bookmarks"
        const val ABOUT_TITLE = "About"

        const val WAIT_TIME = 5000L // 5 seconds
        const val TOTAL_DATA_SHOWN = 20 // 20 items per page
    }

}