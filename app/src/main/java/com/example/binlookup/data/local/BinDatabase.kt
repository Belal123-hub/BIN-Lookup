package com.example.binlookup.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BinEntity::class], version = 2)
abstract class BinDatabase : RoomDatabase() {
    abstract fun binDao(): BinDao
}
