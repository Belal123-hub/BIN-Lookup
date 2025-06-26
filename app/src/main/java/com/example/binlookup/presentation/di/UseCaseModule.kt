package com.example.binlookup.presentation.di
import com.example.binlookup.domain.usecase.GetBinHistoryUseCase
import com.example.binlookup.domain.usecase.GetBinInfoUseCase
import com.example.binlookup.domain.usecase.SaveBinInfoUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetBinInfoUseCase(get()) }
    single { GetBinHistoryUseCase(get()) }
    single { SaveBinInfoUseCase(get()) }
}