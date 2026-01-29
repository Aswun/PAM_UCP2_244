package com.example.prak9.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Kategori::class], version = 1, exportSchema = false)
abstract class DatabaseKategori : RoomDatabase() {

    abstract fun kategoriDao() : KategoriDao

    companion object {
        @Volatile
        private var Instance : DatabaseKategori? = null

        fun getDatabase(context: Context) : DatabaseKategori {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context, DatabaseKategori::class.java,
                    "kategori_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}

@Database(entities = [Buku::class], version = 1, exportSchema = false)
abstract class DatabaseBuku : RoomDatabase() {

    abstract fun bukuDao() : BukuDao

    companion object {
        @Volatile
        private var Instance : DatabaseBuku? = null

        fun getDatabase(context: Context) : DatabaseBuku {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context, DatabaseBuku::class.java,
                    "Buku_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}