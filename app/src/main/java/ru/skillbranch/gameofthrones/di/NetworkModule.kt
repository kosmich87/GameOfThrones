package ru.skillbranch.gameofthrones.di

import android.app.Application
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.skillbranch.gameofthrones.AppConfig
import ru.skillbranch.gameofthrones.api.GameOfThronesApi
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])

class NetworkModule {

    var gsonConverter: GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideOkHttpClient(appContext: Application): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.addNetworkInterceptor(StethoInterceptor())
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideGameOfThronesApi(okHttpClient: OkHttpClient): GameOfThronesApi {
        return Retrofit.Builder()
            .baseUrl(AppConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GameOfThronesApi::class.java)
    }
}