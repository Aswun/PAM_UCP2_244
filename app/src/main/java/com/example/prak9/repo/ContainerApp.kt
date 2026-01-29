package com.example.prak9.repo

import android.app.Application
import android.content.Context
import com.example.prak9.room.DatabasePerpustakaan

interface ContainerApp {
    val repoKategori: RepoKategori
    val repoBuku: RepoBuku
}

class ContainerDataApp(private val context: Context) : ContainerApp {
    // Inisialisasi Database tunggal
    private val database: DatabasePerpustakaan by lazy {
        DatabasePerpustakaan.getDatabase(context)
    }

    override val repoKategori: RepoKategori by lazy {
        OfflineRepoKategori(database.kategoriDao())
    }

    override val repoBuku: RepoBuku by lazy {
        OfflineRepoBuku(database.bukuDao())
    }
}

class AplikasiKategoriBuku : Application() {
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}