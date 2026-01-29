package com.example.ucp2.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "tblKategori")
data class Kategori(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String
)

@Entity(
    tableName = "tblBuku",
    foreignKeys = [ForeignKey(
        entity = Kategori::class,
        parentColumns = ["id"],
        childColumns = ["idKategori"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Buku(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val judulBuku: String,
    val pengarang: String,
    val tglMasuk: String,
    val idKategori: Int
)