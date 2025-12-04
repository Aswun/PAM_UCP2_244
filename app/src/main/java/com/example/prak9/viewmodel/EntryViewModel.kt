package com.example.prak9.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.prak9.repo.RepoSiswa
import com.example.prak9.room.Siswa

class EntryViewModel(private val repoSiswa: RepoSiswa) : ViewModel() {

    /**
     * Berisi status Siswa saat ini
     */
    var uiStateSiswa by mutableStateOf(value = UiStateSiswa())
        private set

    /**
     * Fungsi untuk memvalidasi input
     */
    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean {
        return with(receiver = uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa =
            UiStateSiswa(
                detailSiswa = detailSiswa,
                isEntryValid = validasiInput(uiState = detailSiswa)
            )
    }

    /**
     * Fungsi untuk menyimpan data yang di-entry
     */
    suspend fun saveSiswa() {
        if (validasiInput()) {
            repoSiswa.insertSiswa(siswa = uiStateSiswa.detailSiswa.toSiswa())
        }
    }
}

/**
 * Mewakili Status UI untuk Siswa.
 */
data class UiStateSiswa(
    val detailSiswa: DetailSiswa = DetailSiswa(),
    val isEntryValid: Boolean = false
)

data class DetailSiswa(
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val email: String = "",
    val telpon: String = ""
)

/* Fungsi untuk mengkonversi data input ke data dalam tabel sesuai jenis datanya */
fun DetailSiswa.toSiswa(): Siswa = Siswa(
    id = id,
    nama = nama,
    alamat = alamat,
    email = email,
    telpon = telpon
)

// Fungsi konversi dari entitas Siswa ke DetailSiswa (UI-friendly)
fun Siswa.toDetailSiswa(): DetailSiswa = DetailSiswa(
    id = id,
    nama = nama,
    alamat = alamat,
    email = email,
    telpon = telpon
)

// Fungsi untuk mengkonversi dari entitas Siswa ke UiStateSiswa
fun Siswa.toUiStateSiswa(isEntryValid: Boolean = false): UiStateSiswa = UiStateSiswa(
    detailSiswa = this.toDetailSiswa(),
    isEntryValid = isEntryValid
)