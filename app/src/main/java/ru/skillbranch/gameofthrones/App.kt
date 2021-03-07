package ru.skillbranch.gameofthrones

import android.app.Application
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ru.skillbranch.gameofthrones.di.AppInjector
import javax.inject.Inject

class App: Application(), HasAndroidInjector {

    @Inject lateinit var androidInjector : DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        AppInjector.init(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}