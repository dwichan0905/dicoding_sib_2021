package id.dwichan.moviedicts

import android.app.Application
import id.dwichan.moviedicts.core.di.CoreComponent
import id.dwichan.moviedicts.core.di.DaggerCoreComponent
import id.dwichan.moviedicts.di.AppComponent
import id.dwichan.moviedicts.di.DaggerAppComponent

open class MyApplication : Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}