package com.example.gdirectionspoc.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.gdirectionspoc.db.ApplicationDatabase
import com.example.gdirectionspoc.network.ApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @MapUrl
    @Provides
    @Singleton
    fun provideMapUrl() = "https://maps.googleapis.com/maps/api/"

    @ApiKey
    @Provides
    @Singleton
    fun provideApiKey() = "AIzaSyA6tGKklWqUTK2BahzhncAJ8-xg9p03TfE"

    @PlacesApiKey
    @Provides
    @Singleton
    fun providePlacesApiKey() = "AIzaSyDo3gLV1Sd7EwKCnLQvd-ZdxEN2FQs1uNM"

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, @MapUrl BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        ApplicationDatabase::class.java, "app database")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideGDirectionDao(appDatabase: ApplicationDatabase) = appDatabase.gDirectionDao()

}