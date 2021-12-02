package id.dwichan.moviedicts.ui.main

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.google.android.material.tabs.TabLayout
import id.dwichan.moviedicts.R
import id.dwichan.moviedicts.core.util.IdlingResources
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var uiDevice: UiDevice

    @Before
    fun setup() {
        Intents.init()
        IdlingRegistry.getInstance().register(IdlingResources.idlingResource)
        uiDevice = UiDevice.getInstance(getInstrumentation())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResources.idlingResource)
        Intents.release()
    }

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

        Thread.sleep(HOLD_PAGING_TIME)
        onView(withId(R.id.rec_movies_trending_today)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.image_movie_poster)).check(matches(isDisplayed()))

        onView(withId(R.id.text_movie_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_movie_title)).check(matches(not(withText(""))))

        onView(withId(R.id.text_released_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.text_released_duration)).check(matches(not(withText(""))))

        onView(withId(R.id.text_genres)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genres)).check(matches(not(withText(""))))

        onView(withId(R.id.pb_user_score)).check(matches(isDisplayed()))

        onView(withId(R.id.text_user_score)).check(matches(isDisplayed()))
        onView(withId(R.id.text_user_score)).check(matches(not(withText(""))))
    }

    @Test
    fun shouldTrendingWeeklyMovieItemsAreValid() {
        onView(withId(R.id.navigation_movies)).perform(click())

        Thread.sleep(HOLD_PAGING_TIME)
        onView(withId(R.id.rec_movies_trending_weekly)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.image_movie_poster)).check(matches(isDisplayed()))

        onView(withId(R.id.text_movie_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_movie_title)).check(matches(not(withText(""))))

        onView(withId(R.id.text_released_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.text_released_duration)).check(matches(not(withText(""))))

        onView(withId(R.id.text_genres)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genres)).check(matches(not(withText(""))))

        onView(withId(R.id.pb_user_score)).check(matches(isDisplayed()))

        onView(withId(R.id.text_user_score)).check(matches(isDisplayed()))
        onView(withId(R.id.text_user_score)).check(matches(not(withText(""))))
    }

    @Test
    fun shouldTrendingDailyTvShowItemsAreValid() {
        onView(withId(R.id.navigation_television)).perform(click())

        Thread.sleep(HOLD_PAGING_TIME)
        onView(withId(R.id.rec_tv_trending_today)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.image_tv_poster)).check(matches(isDisplayed()))

        onView(withId(R.id.text_tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_tv_title)).check(matches(not(withText(""))))

        onView(withId(R.id.text_first_air_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.text_first_air_duration)).check(matches(not(withText(""))))

        onView(withId(R.id.text_genres)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genres)).check(matches(not(withText(""))))

        onView(withId(R.id.pb_user_score)).check(matches(isDisplayed()))

        onView(withId(R.id.text_user_score)).check(matches(isDisplayed()))
        onView(withId(R.id.text_user_score)).check(matches(not(withText(""))))
    }

    @Test
    fun shouldTrendingWeeklyTvShowItemsAreValid() {
        onView(withId(R.id.navigation_television)).perform(click())

        Thread.sleep(HOLD_PAGING_TIME)
        onView(withId(R.id.rec_tv_trending_today)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.image_tv_poster)).check(matches(isDisplayed()))

        onView(withId(R.id.text_tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_tv_title)).check(matches(not(withText(""))))

        onView(withId(R.id.text_first_air_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.text_first_air_duration)).check(matches(not(withText(""))))

        onView(withId(R.id.text_genres)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genres)).check(matches(not(withText(""))))

        onView(withId(R.id.pb_user_score)).check(matches(isDisplayed()))

        onView(withId(R.id.text_user_score)).check(matches(isDisplayed()))
        onView(withId(R.id.text_user_score)).check(matches(not(withText(""))))
    }

    @Test
    fun shouldBookmarkMovieWorked() {
        onView(withId(R.id.navigation_bookmark)).perform(click())

        // check "not found" is displayed
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(0))
        onView(withId(R.id.anim_movie_not_found)).check(matches(isDisplayed()))
        onView(withId(R.id.text_movie_not_found)).check(matches(isDisplayed()))

        // add to bookmark
        onView(withId(R.id.navigation_movies)).perform(click())
        Thread.sleep(HOLD_PAGING_TIME)
        onView(withId(R.id.rec_movies_trending_today)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.button_bookmark)).perform(click())
        Espresso.pressBack()

        // check and remove from bookmark
        onView(withId(R.id.navigation_bookmark)).perform(click())
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(0))
        // check "not found" message is gone
        Thread.sleep(HOLD_PAGING_TIME)
        onView(withId(R.id.rec_movie_bookmarks)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.button_bookmark)).perform(click())
        Espresso.pressBack()

        // check "not found" is displayed
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(0))
        onView(withId(R.id.anim_movie_not_found)).check(matches(isDisplayed()))
        onView(withId(R.id.text_movie_not_found)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldBookmarkTvShowWorked() {
        onView(withId(R.id.navigation_bookmark)).perform(click())

        // check "not found" is displayed
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(1))
        onView(withId(R.id.anim_tv_not_found)).check(matches(isDisplayed()))
        onView(withId(R.id.text_tv_not_found)).check(matches(isDisplayed()))

        // add to bookmark
        onView(withId(R.id.navigation_television)).perform(click())
        Thread.sleep(HOLD_PAGING_TIME)
        onView(withId(R.id.rec_tv_trending_today)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.button_bookmark)).perform(click())
        Espresso.pressBack()

        // check and remove from bookmark
        onView(withId(R.id.navigation_bookmark)).perform(click())
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(1))
        // check "not found" message is gone
        Thread.sleep(HOLD_PAGING_TIME)
        onView(withId(R.id.rec_tv_bookmarks)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.button_bookmark)).perform(click())
        Espresso.pressBack()

        // check "not found" is displayed
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(1))
        onView(withId(R.id.anim_tv_not_found)).check(matches(isDisplayed()))
        onView(withId(R.id.text_tv_not_found)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldMovieDetailsCanBeRefreshed() {
        onView(withId(R.id.navigation_movies)).perform(click())
        Thread.sleep(HOLD_PAGING_TIME)
        onView(withId(R.id.rec_movies_trending_today)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.menu_refresh)).perform(click())
        Espresso.pressBack()

        onView(withId(R.id.navigation_movies)).perform(click())
        Thread.sleep(HOLD_PAGING_TIME)
        onView(withId(R.id.rec_movies_trending_weekly)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.menu_refresh)).perform(click())
    }

    @Test
    fun shouldTelevisionShowDetailsCanBeRefreshed() {
        onView(withId(R.id.navigation_television)).perform(click())
        Thread.sleep(HOLD_PAGING_TIME)
        onView(withId(R.id.rec_tv_trending_today)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.menu_refresh)).perform(click())
        Espresso.pressBack()

        onView(withId(R.id.navigation_television)).perform(click())
        Thread.sleep(HOLD_PAGING_TIME)
        onView(withId(R.id.rec_tv_trending_weekly)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.menu_refresh)).perform(click())
    }

    @Test
    fun shouldMovieDetailsCanBeShared() {
        onView(withId(R.id.navigation_movies)).perform(click())
        Thread.sleep(HOLD_PAGING_TIME)
        onView(withId(R.id.rec_movies_trending_today)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.menu_share)).perform(click())
        intended(hasAction(Intent.ACTION_CHOOSER))
        uiDevice.pressBack()
    }

    @Test
    fun shouldTelevisionShowDetailsCanBeShared() {
        onView(withId(R.id.navigation_television)).perform(click())
        Thread.sleep(HOLD_PAGING_TIME)
        onView(withId(R.id.rec_tv_trending_today)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.menu_share)).perform(click())
        intended(hasAction(Intent.ACTION_CHOOSER))
        uiDevice.pressBack()
    }

    @Test
    fun shouldAboutAllFeatureWorked() {
        val packageName = uiDevice.launcherPackageName
        val myFacebook = Uri.parse("https://web.facebook.com/CdrScNET89")
        val myGitHub = Uri.parse("https://github.com/dwichan0905")
        val myEmail = Uri.parse("mailto:dwichan@outlook.com")

        onView(withId(R.id.navigation_about)).perform(click())

        // Visit Facebook
        onView(withId(R.id.list_options)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        intended(allOf(hasAction(Intent.ACTION_VIEW), hasData(myFacebook)))
        uiDevice.pressBack()
        uiDevice.wait(
            Until.hasObject(By.pkg(packageName).depth(0)),
            LAUNCH_TIMEOUT
        )

        // Visit GitHub
        onView(withId(R.id.list_options)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        intended(allOf(hasAction(Intent.ACTION_VIEW), hasData(myGitHub)))
        uiDevice.pressBack()
        uiDevice.wait(
            Until.hasObject(By.pkg(packageName).depth(0)),
            LAUNCH_TIMEOUT
        )

        // Send me an Email
        onView(withId(R.id.list_options)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            )
        )
        intended(allOf(hasAction(Intent.ACTION_SENDTO), hasData(myEmail)))
        uiDevice.pressBack()
        uiDevice.pressBack()
        uiDevice.wait(
            Until.hasObject(By.pkg(packageName).depth(0)),
            LAUNCH_TIMEOUT
        )
    }

    private fun selectTabAtPosition(tabIndex: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> =
                allOf(isDisplayed(), isAssignableFrom(TabLayout::class.java))

            override fun getDescription(): String = "select tab at index: $tabIndex"

            override fun perform(uiController: UiController?, view: View?) {
                val tabLayout = view as TabLayout
                val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex)
                    ?: throw PerformException.Builder()
                        .withCause(Throwable("No tab at index $tabIndex"))
                        .build()

                tabAtIndex.select()
            }
        }
    }

    companion object {
        const val MOVIE_TITLE = "Movie"
        const val TELEVISION_SHOW_TITLE = "Television Show"
        const val BOOKMARKS_TITLE = "Bookmarks"
        const val ABOUT_TITLE = "About"

        const val HOLD_PAGING_TIME = 1000L // 1 second
        const val LAUNCH_TIMEOUT = 1000L // 1 second
    }
}