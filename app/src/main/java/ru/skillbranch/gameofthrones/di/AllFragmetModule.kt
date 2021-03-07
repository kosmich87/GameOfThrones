package ru.skillbranch.gameofthrones.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.skillbranch.gameofthrones.ui.character.CharacterFragment
import ru.skillbranch.gameofthrones.ui.houses.HousesFragment
import ru.skillbranch.gameofthrones.ui.splash.SplashFragment

@Module
abstract class AllFragmetModule {

    @ContributesAndroidInjector()
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector()
    abstract fun contributeHousesFragment(): HousesFragment

    @ContributesAndroidInjector()
    abstract fun contributeCharacterFragment(): CharacterFragment
}
