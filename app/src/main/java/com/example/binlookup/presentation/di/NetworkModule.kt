package com.example.binlookup.presentation.di

import com.example.binlookup.data.remote.BinApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<BinApiService> {
        get<Retrofit>().create(BinApiService::class.java)
    }
}
