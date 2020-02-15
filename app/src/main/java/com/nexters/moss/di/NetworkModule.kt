package com.nexters.moss.di

import com.nexters.moss.api.HabitApi
import com.nexters.moss.api.UserApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    single {
        GsonConverterFactory.create() as Converter.Factory
    }

    single {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(get())
            .baseUrl("http://49.50.167.14/")
            .build()
            .create(HabitApi::class.java)
    }

    single {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(get())
            .baseUrl("http://49.50.167.14/")
            .build()
            .create(UserApi::class.java)
    }
}