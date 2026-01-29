package com.example.prak9.repo

import android.app.Application
import android.content.Context
import com.example.prak9.room.DatabaseBuku
import com.example.prak9.room.DatabaseKategori
import com.example.prak9.room.DatabaseSiswa

/**
interface ContainerApp {
    val repoSiswa : RepoSiswa
}

class ContainerDataApp(private val context: Context) :
    ContainerApp {
        override val repoSiswa : RepoSiswa by lazy {
        OfflineRepoSiswa(
            DatabaseSiswa.
            getDatabase(context).
            siswaDao())
    }
}

class AplikasiSiswa : Application() {
    /**
     * AppContainer instance digunakan oleh kelas-kelas lainnya untuk mendapatkan dependensi
     */
     lateinit var container: ContainerApp

     override fun onCreate() {
         super.onCreate()
         container = ContainerDataApp(this)
     }
}
 **/

interface ContainerApp {
    val repoKategori : RepoKategori
    val repoBuku : RepoBuku
}

class ContainerDataApp(private val context: Context) :
    ContainerApp {
    override val repoKategori : RepoKategori by lazy {
        OfflineRepoKategori(
            DatabaseKategori.
            getDatabase(context).
            kategoriDao())
    }
    override val repoBuku : RepoBuku by lazy {
        OfflineRepoBuku(
            DatabaseBuku.
            getDatabase(context).
            bukuDao())
    }
}

class AplikasiKategoriBuku : Application() {
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}