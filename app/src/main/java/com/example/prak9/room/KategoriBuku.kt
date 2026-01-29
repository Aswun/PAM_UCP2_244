package com.example.prak9.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import java.util.Date

@Entity(tableName = "tblKategori")
data class Kategori(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val nama : String
)

@Entity(tableName = "tblBuku")
data class Buku(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val judulBuku : String,
    val pengarang : String,
    val tglMasuk : Date,

)