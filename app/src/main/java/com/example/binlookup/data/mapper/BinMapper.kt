package com.example.binlookup.data.mapper

import com.example.binlookup.data.model.BinResponse
import com.example.binlookup.domain.model.Bank
import com.example.binlookup.domain.model.BinInfo
import com.example.binlookup.domain.model.Country

fun BinResponse.toDomain(): BinInfo {
    return BinInfo(
        bin = bin,
        scheme = scheme,
        type = type,
        brand = brand,
        prepaid = prepaid,
        country = country?.let {
            Country(
                name = it.name,
                emoji = it.emoji,
                currency = it.currency,
                latitude = it.latitude,
                longitude = it.longitude
            )
        },
        bank = bank?.let {
            Bank(
                name = it.name,
                url = it.url,
                phone = it.phone,
                city = it.city
            )
        }
    )
}
