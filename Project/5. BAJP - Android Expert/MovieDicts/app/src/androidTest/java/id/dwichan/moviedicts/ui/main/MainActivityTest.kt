package id.dwichan.moviedicts.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import id.dwichan.moviedicts.R
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test fun shouldMoviesFragmentValid() {
        onView(withId(R.id.navigation_movies)).perform(click())
        onView(withId(R.id.fragment_movies)).check(
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

    @Test fun shouldTelevisionShowFragmentValid() {
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

    @Test fun shouldBookmarkFragmentValid() {
        onView(withId(R.id.navigation_bookmark)).perform(click())
        onView(withId(R.id.fragment_bookmark)).check(matches(
            isDisplayed())
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

    @Test fun shouldAboutFragmentValid() {
        onView(withId(R.id.navigation_about)).perform(click())
        onView(withId(R.id.fragment_about)).check(matches(
            isDisplayed())
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

    companion object {
        const val MOVIE_TITLE = "Movie"
        const val TELEVISION_SHOW_TITLE = "Television Show"
        const val BOOKMARKS_TITLE = "Bookmarks"
        const val ABOUT_TITLE = "About"
    }

}