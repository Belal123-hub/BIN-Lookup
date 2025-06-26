package com.example.binlookup.presentation.di

import com.example.binlookup.ui.screen.BinViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule  = module {
    viewModel { BinViewModel(get()) }

}
