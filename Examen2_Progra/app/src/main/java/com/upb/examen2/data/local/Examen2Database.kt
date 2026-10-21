package com.upb.examen2.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [RazaDatoEntity::class, ClimaDatoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class Examen2Database : RoomDatabase() {

    abstract fun razaDatoDao(): RazaDatoDao

    abstract fun climaDatoDao(): ClimaDatoDao

    companion object {
        @Volatile
        private var instancia: Examen2Database? = null

        fun obtener(context: Context): Examen2Database =
            instancia ?: synchronized(this) {
                instancia ?: Room.databaseBuilder(
                    context.applicationContext,
                    Examen2Database::class.java,
                    "examen2_apis.db"
                ).build().also { instancia = it }
            }
    }
}