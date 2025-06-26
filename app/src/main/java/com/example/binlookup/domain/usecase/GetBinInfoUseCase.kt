package com.example.binlookup.domain.usecase

import com.example.binlookup.domain.BinRepository
import com.example.binlookup.domain.model.BinInfo

class GetBinInfoUseCase(
    private val repository: BinRepository
) {
    suspend operator fun invoke(bin: String): BinInfo {
        return repository.getBinInfo(bin)
    }
}
