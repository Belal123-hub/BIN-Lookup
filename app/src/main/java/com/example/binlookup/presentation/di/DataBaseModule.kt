package com.example.binlookup.presentation.di

import androidx.room.Room
import org.koin.dsl.module
import android.app.Application
import com.example.binlookup.data.local.BinDao
import com.example.binlookup.data.local.BinDatabase

val databaseModule = module {

    single {
        Room.databaseBuilder(
            get<Application>(),
            BinDatabase::class.java,
            "bin_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    single<BinDao> {
        get<BinDatabase>().binDao()
    }
}
