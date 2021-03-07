package ru.skillbranch.gameofthrones.api

import androidx.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Url
import ru.skillbranch.gameofthrones.data.remote.res.CharacterRes
import ru.skillbranch.gameofthrones.data.remote.res.HouseRes

interface GameOfThronesApi {

    @GET("houses")
    suspend fun getHouses(): List<HouseRes>

    @GET
    fun getHouseInfo(@Url url: String): LiveData<HouseRes>

    @GET
    fun getCharacter(@Url url: String): LiveData<CharacterRes>
}