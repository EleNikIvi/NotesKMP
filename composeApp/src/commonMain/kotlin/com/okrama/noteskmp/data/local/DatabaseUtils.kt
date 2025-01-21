package com.okrama.noteskmp.data.local

import androidx.sqlite.SQLiteException
import co.touchlab.kermit.Logger

object DatabaseUtils {
    inline fun safeLaunch(finally: () -> Unit = {}, block: () -> Unit) {
        try {
            block()
        } catch (e: SQLiteException) {
            Logger.e("Constraint fail $e")
        } catch (e: Exception) {
            Logger.e("Database unknown exception: $e")
        }
    }
}
