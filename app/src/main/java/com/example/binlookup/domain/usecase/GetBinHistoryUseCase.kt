package com.example.binlookup.domain.usecase

import com.example.binlookup.domain.BinRepository
import com.example.binlookup.domain.model.BinInfo

class GetBinHistoryUseCase(
    private val repository: BinRepository
) {
    suspend operator fun invoke(): List<BinInfo> {
        return repository.getBinHistory()
    }
}