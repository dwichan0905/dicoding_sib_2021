package com.dicoding.courseschedule.ui.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.ui.add.AddCourseActivity
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    @get:Rule
    var rule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun shouldAddMenuWorked() {
        onView(withId(R.id.action_add)).perform(click())
        intended(hasComponent(AddCourseActivity::class.java.name))
    }

    @Test
    fun shouldAddButtonWorked() {
        onView(withId(R.id.action_list)).perform(click())
        onView(withId(R.id.fab)).perform(click())
        intended(hasComponent(AddCourseActivity::class.java.name))
    }
}