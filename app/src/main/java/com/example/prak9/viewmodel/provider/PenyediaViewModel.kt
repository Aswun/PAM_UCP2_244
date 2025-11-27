package com.example.prak9.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.prak9.repo.AplikasiSiswa
import com.example.prak9.viewmodel.EntryViewModel
import com.example.prak9.viewmodel.HomeViewModel

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
    }
}

/**
 * Fungsi ekstensi untuk mendapatkan instance [AplikasiSiswa] dari [CreationExtras]
 */
fun CreationExtras.aplikasiSiswa(): AplikasiSiswa =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiSiswa)