package com.example.binlookup.data.mapper

import com.example.binlookup.data.local.BinEntity
import com.example.binlookup.domain.model.Bank
import com.example.binlookup.domain.model.BinInfo
import com.example.binlookup.domain.model.Country

fun BinInfo.toEntity(): BinEntity {
    return BinEntity(
        bin = bin,
        scheme = scheme,
        type = type,
        brand = brand,
        prepaid = prepaid,
        country = country?.name,
        emoji = country?.emoji,
        currency = country?.currency,
        latitude = country?.latitude,
        longitude = country?.longitude,
        bankName = bank?.name,
        url = bank?.url,
        phone = bank?.phone,
        city = bank?.city
    )
}

fun BinEntity.toDomain(): BinInfo {
    return BinInfo(
        bin = bin,
        scheme = scheme,
        type = type,
        brand = brand,
        prepaid = prepaid,
        country = Country(
            name = country,
            emoji = emoji,
            currency = currency,
            latitude = latitude,
            longitude = longitude
        ),
        bank = Bank(
            name = bankName,
            url = url,
            phone = phone,
            city = city
        )
    )
}
