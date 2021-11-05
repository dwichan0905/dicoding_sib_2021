package id.dwichan.moviedicts.util

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    private val idling = CountingIdlingResource(RESOURCE)

    fun increment() {
        idling.increment()
    }

    fun decrement() {
        idling.decrement()
    }

    fun getIdlingResources(): IdlingResource = idling
}