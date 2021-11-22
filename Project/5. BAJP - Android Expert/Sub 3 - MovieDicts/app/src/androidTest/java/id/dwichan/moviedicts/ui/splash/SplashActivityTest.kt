package id.dwichan.moviedicts.ui.splash

import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.ext.junit.rules.ActivityScenarioRule
import id.dwichan.moviedicts.ui.main.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SplashActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(SplashActivity::class.java)

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun final() {
        Intents.release()
    }

    @Test
    fun shouldMainActivityVisibleAfterSplash() {
        Thread.sleep(SPLASH_WAIT_TIME)
        intended(hasComponent(MainActivity::class.java.name))
    }

    companion object {
        const val SPLASH_WAIT_TIME = 3000L
    }
}