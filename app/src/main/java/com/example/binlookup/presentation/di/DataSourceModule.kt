package com.example.binlookup.presentation.di

import androidx.room.Room
import com.example.binlookup.Application
import com.example.binlookup.data.local.BinDao
import com.example.binlookup.data.local.BinDatabase
import org.koin.dsl.module

val dataSourceModule = module {
    single {
        Room.databaseBuilder(
            get<Application>(),
            BinDatabase::class.java,
            "bin_db"
        ).build()
    }

    single<BinDao> { get<BinDatabase>().binDao() }
}