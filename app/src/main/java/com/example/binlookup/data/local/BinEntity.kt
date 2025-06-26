package com.example.binlookup.data.local
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bin_history")
data class BinEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val bin: String?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: String?,  // name
    val emoji: String?,
    val currency: String?,
    val latitude: Double?,
    val longitude: Double?,
    val bankName: String?,
    val url: String?,
    val phone: String?,
    val city: String?
)
