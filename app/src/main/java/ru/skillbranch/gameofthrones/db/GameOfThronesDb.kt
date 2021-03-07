package ru.skillbranch.gameofthrones.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.skillbranch.gameofthrones.data.local.entities.Character
import ru.skillbranch.gameofthrones.data.local.entities.House

const val DATABASE_NAME = "gameofthrones.db"

@Database(entities = arrayOf(House::class, Character::class), version = 1, exportSchema = false)

abstract class GameOfThronesDb: RoomDatabase() {
    abstract fun houseDao(): HouseDao
    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile
        private var INSTANCE: GameOfThronesDb? = null

        fun getInstance(context: Context): GameOfThronesDb =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it}
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
            GameOfThronesDb::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}