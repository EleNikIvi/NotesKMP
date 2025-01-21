package com.okrama.noteskmp.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<NotesKMPDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("notes_kmp_room.db")
    return Room.databaseBuilder<NotesKMPDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    ).setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
}

fun getDatabase(ctx: Context): NotesKMPDatabase {
    return getDatabaseBuilder(ctx).build()
}