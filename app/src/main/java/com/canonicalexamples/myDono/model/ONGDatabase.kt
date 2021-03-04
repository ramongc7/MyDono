package com.canonicalexamples.myDono.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Ong::class], version = 1, exportSchema = false)
abstract class ONGDatabase: RoomDatabase() {
    abstract val ongDAO: OngDAO

    companion object {
        @Volatile
        private var INSTANCE: ONGDatabase? = null
        fun getInstance(context: Context): ONGDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ONGDatabase::class.java,
                    "my_dono_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
