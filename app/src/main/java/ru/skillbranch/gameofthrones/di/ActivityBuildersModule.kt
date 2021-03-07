package ru.skillbranch.gameofthrones.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.skillbranch.gameofthrones.ui.MainActivity

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [AllFragmetModule::class])
    abstract fun contributeMainActivity(): MainActivity
}