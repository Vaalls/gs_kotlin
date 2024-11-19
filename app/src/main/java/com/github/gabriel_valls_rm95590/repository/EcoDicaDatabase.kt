package com.github.gabriel_valls_rm95590.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.gabriel_valls_rm95590.model.EcoDica

@Database(entities = [EcoDica::class], version = 1)
abstract class EcoDicaDatabase : RoomDatabase() {
    abstract fun ecoDicaDao(): EcoDicaDao

    companion object {
        @Volatile
        private var INSTANCE: EcoDicaDatabase? = null

        fun getDatabase(context: Context): EcoDicaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EcoDicaDatabase::class.java,
                    "eco_dica_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}