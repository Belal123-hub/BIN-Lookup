package com.example.binlookup.domain.model

data class BinInfo(
    val bin: String?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: Country?,
    val bank: Bank?
)

data class Country(
    val name: String?,
    val emoji: String?,
    val currency: String?,
    val latitude: Double?,
    val longitude: Double?
)

data class Bank(
    val name: String?,
    val url: String?,
    val phone: String?,
    val city: String?
)
