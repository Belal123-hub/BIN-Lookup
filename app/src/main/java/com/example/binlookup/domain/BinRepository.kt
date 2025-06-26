package com.example.binlookup.domain

import com.example.binlookup.domain.model.BinInfo

interface BinRepository {
    suspend fun getBinInfo(bin: String): BinInfo
    suspend fun saveBinInfo(binInfo: BinInfo)
    suspend fun getBinHistory(): List<BinInfo>
}
