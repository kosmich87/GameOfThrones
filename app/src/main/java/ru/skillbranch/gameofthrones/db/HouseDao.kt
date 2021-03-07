package ru.skillbranch.gameofthrones.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import ru.skillbranch.gameofthrones.data.local.entities.House


@Dao
abstract class HouseDao {

    @Query("SELECT * FROM House ORDER BY id")
    abstract fun loadHouses(): LiveData<List<House>>
}