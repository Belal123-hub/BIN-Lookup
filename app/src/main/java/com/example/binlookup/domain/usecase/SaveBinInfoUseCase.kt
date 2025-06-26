package com.example.binlookup.domain.usecase

import com.example.binlookup.domain.BinRepository
import com.example.binlookup.domain.model.BinInfo

class SaveBinInfoUseCase(
    private val repository: BinRepository
) {
    suspend operator fun invoke(binInfo: BinInfo) {
        repository.saveBinInfo(binInfo)
    }
}