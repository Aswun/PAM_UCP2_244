package com.example.ucp2.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface KategoriDao {
    @Query("SELECT * from tblKategori ORDER BY nama ASC")
    fun getAllKategori(): Flow<List<Kategori>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(kategori: Kategori)

    @Query("SELECT * from tblKategori WHERE id = :id")
    fun getKategori(id: Int): Flow<Kategori>

    @Update
    suspend fun update(kategori: Kategori)

    @Delete
    suspend fun delete(kategori: Kategori)
}

@Dao
interface BukuDao {
    @Query("SELECT * from tblBuku ORDER BY judulBuku ASC")
    fun getAllBuku(): Flow<List<Buku>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(buku: Buku)

    @Query("SELECT * from tblBuku WHERE id = :id")
    fun getBuku(id: Int): Flow<Buku>

    @Update
    suspend fun update(buku: Buku)

    @Delete
    suspend fun delete(buku: Buku)
}