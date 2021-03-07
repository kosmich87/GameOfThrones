package ru.skillbranch.gameofthrones.di

import android.app.Application
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import ru.skillbranch.gameofthrones.db.CharacterDao
import ru.skillbranch.gameofthrones.db.GameOfThronesDb
import ru.skillbranch.gameofthrones.db.HouseDao
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module(includes = [(ViewModelModule::class)])
class AppModule {

    @Provides
    fun provideUiContext(): CoroutineContext{
        return Dispatchers.Main
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): GameOfThronesDb {
        return GameOfThronesDb.getInstance(app)
    }

    @Singleton
    @Provides
    fun provideHouseDao(db: GameOfThronesDb): HouseDao {
        return db.houseDao()
    }

    @Singleton
    @Provides
    fun provideCharacterDao(db: GameOfThronesDb): CharacterDao {
        return db.characterDao()
    }
}