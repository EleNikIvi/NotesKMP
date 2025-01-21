package com.okrama.noteskmp.data.local

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory

fun getDatabase(): NotesKMPDatabase {
    val dbFile = NSHomeDirectory() + "/notes_kmp_room.db"
    return Room.databaseBuilder<NotesKMPDatabase>(
        name = dbFile,
        factory = { NotesKMPDatabase::class.instantiateImpl() }
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}