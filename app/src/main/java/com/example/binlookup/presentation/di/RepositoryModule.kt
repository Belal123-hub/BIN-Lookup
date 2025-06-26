package com.example.binlookup.presentation.di

import com.example.binlookup.data.BinRepositoryImpl
import com.example.binlookup.domain.BinRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<BinRepository> {
        BinRepositoryImpl(
            api = get(),
            dao = get()
        )
    }
}
