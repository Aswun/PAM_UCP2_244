package com.example.prak9.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.prak9.repo.AplikasiKategoriBuku
import com.example.prak9.viewmodel.DetailViewModel
import com.example.prak9.viewmodel.EditViewModel
import com.example.prak9.viewmodel.EntryViewModel
import com.example.prak9.viewmodel.HomeViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                aplikasiPustaka().container.repoKategori,
                aplikasiPustaka().container.repoBuku
            )
        }
        initializer {
            EntryViewModel(
                aplikasiPustaka().container.repoKategori,
                aplikasiPustaka().container.repoBuku
            )
        }
        initializer {
            DetailViewModel(
                this.createSavedStateHandle(),
                aplikasiPustaka().container.repoBuku
            )
        }
        initializer {
            EditViewModel(
                this.createSavedStateHandle(),
                aplikasiPustaka().container.repoBuku,
                aplikasiPustaka().container.repoKategori
            )
        }
    }
}

/**
 * Fungsi ekstensi untuk mendapatkan instance [AplikasiKategoriBuku]
 */
fun CreationExtras.aplikasiPustaka(): AplikasiKategoriBuku =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiKategoriBuku)