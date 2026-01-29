package com.example.prak9.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.prak9.repo.AplikasiKategoriBuku
import com.example.prak9.viewmodel.EntryViewModel
import com.example.prak9.viewmodel.HomeViewModel
import com.example.prak9.viewmodel.DetailViewModel
import com.example.prak9.viewmodel.EditViewModel

/**
/**
 * Menyediakan objek [ViewModelProvider.Factory] untuk pembuatan instance [HomeViewModel] dan [EntryViewModel]
 */
object PenyediaViewModel {
    val Factory = viewModelFactory {
        // Initializer untuk HomeViewModel
        initializer {
            HomeViewModel(
                aplikasiSiswa().container.repoSiswa
            )
        }
        // Initializer untuk EntryViewModel
        initializer {
            EntryViewModel(
                aplikasiSiswa().container.repoSiswa
            )
        }
        //edit : tambah initializer untuk DetailViewModel dan EditViewModel
        initializer {
            DetailViewModel(
                this.createSavedStateHandle(),
                aplikasiSiswa().container.repoSiswa
            )
        }
        initializer {
            EditViewModel(
                this.createSavedStateHandle(),
                aplikasiSiswa().container.repoSiswa
            )
        }
    }
}

/**
 * Fungsi ekstensi untuk mendapatkan instance [AplikasiSiswa] dari [CreationExtras]
 */
fun CreationExtras.aplikasiSiswa(): AplikasiSiswa =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiSiswa)
**/

object PenyediaViewModel {
    val Factory = viewModelFactory {
        // Initializer untuk HomeViewModel
        initializer {
            HomeViewModel(
                AplikasiKategoriBuku().container.repoBuku
            )
        }
        // Initializer untuk EntryViewModel
        initializer {
            EntryViewModel(
                aplikasiSiswa().container.repoSiswa
            )
        }
        //edit : tambah initializer untuk DetailViewModel dan EditViewModel
        initializer {
            DetailViewModel(
                this.createSavedStateHandle(),
                aplikasiSiswa().container.repoSiswa
            )
        }
        initializer {
            EditViewModel(
                this.createSavedStateHandle(),
                aplikasiSiswa().container.repoSiswa
            )
        }
    }
}

/**
 * Fungsi ekstensi untuk mendapatkan instance [AplikasiSiswa] dari [CreationExtras]
 */
fun CreationExtras.aplikasiSiswa(): AplikasiSiswa =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiSiswa)