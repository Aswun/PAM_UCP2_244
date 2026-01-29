package com.example.prak9.repo

import android.app.Application
import android.content.Context
import com.example.prak9.room.DatabasePerpustakaan
import com.example.prak9.room.DatabaseSiswa

interface ContainerApp {
    val repoKategori: RepoKategori
    val repoBuku: RepoBuku
    val repoSiswa: RepoSiswa
}

class ContainerDataApp(private val context: Context) : ContainerApp {
    private val database: DatabasePerpustakaan by lazy {
        DatabasePerpustakaan.getDatabase(context)
    }

    private val databaseSiswa: DatabaseSiswa by lazy {
        DatabaseSiswa.getDatabase(context)
    }

    override val repoKategori: RepoKategori by lazy {
        OfflineRepoKategori(database.kategoriDao())
    }

    override val repoBuku: RepoBuku by lazy {
        OfflineRepoBuku(database.bukuDao())
    }

    override val repoSiswa: RepoSiswa by lazy {
        OfflineRepoSiswa(databaseSiswa.siswaDao())
    }
}

class AplikasiKategoriBuku : Application() {
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}