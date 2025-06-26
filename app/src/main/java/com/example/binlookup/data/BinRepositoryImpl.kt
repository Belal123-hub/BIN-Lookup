package com.example.binlookup.data

import com.example.binlookup.data.local.BinDao
import com.example.binlookup.data.mapper.toDomain
import com.example.binlookup.data.mapper.toEntity
import com.example.binlookup.domain.model.BinInfo
import com.example.binlookup.data.remote.BinApiService
import com.example.binlookup.domain.BinRepository

class BinRepositoryImpl(
    private val api: BinApiService,
    private val dao: BinDao
) : BinRepository {
    override suspend fun getBinInfo(bin: String): BinInfo {
        val response = api.getBinInfo(bin)
        return response.toDomain()
    }

    override suspend fun saveBinInfo(binInfo: BinInfo) {
        dao.insertBin(binInfo.toEntity())
    }

    override suspend fun getBinHistory(): List<BinInfo> {
        return dao.getAllBins().map { it.toDomain() }
    }
}
