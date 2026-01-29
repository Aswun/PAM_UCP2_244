package com.example.ucp2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.repo.RepoBuku
import com.example.ucp2.repo.RepoKategori
import com.example.ucp2.room.Buku
import com.example.ucp2.room.Kategori
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class EntryViewModel(
    private val repoKategori: RepoKategori,
    private val repoBuku: RepoBuku
) : ViewModel() {

    var uiStateBuku by mutableStateOf(UiStateBuku())
        private set

    val kategoriUiState: StateFlow<List<Kategori>> = repoKategori.getAllKategoriStream()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = listOf()
        )

    fun updateUiState(detailBuku: DetailBuku) {
        uiStateBuku = UiStateBuku(
            detailBuku = detailBuku,
            isEntryValid = validasiInput(detailBuku)
        )
    }

    private fun validasiInput(uiState: DetailBuku = uiStateBuku.detailBuku): Boolean {
        return with(uiState) {
            judulBuku.isNotBlank() && pengarang.isNotBlank() && tglMasuk.isNotBlank() && idKategori != 0
        }
    }

    suspend fun saveBuku() {
        if (validasiInput()) {
            repoBuku.insertBuku(uiStateBuku.detailBuku.toBuku())
        }
    }
}

data class UiStateBuku(
    val detailBuku: DetailBuku = DetailBuku(),
    val isEntryValid: Boolean = false
)

data class DetailBuku(
    val id: Int = 0,
    val judulBuku: String = "",
    val pengarang: String = "",
    val tglMasuk: String = "",
    val idKategori: Int = 0,
    val namaKategori: String = ""
)

fun DetailBuku.toBuku(): Buku = Buku(
    id = id,
    judulBuku = judulBuku,
    pengarang = pengarang,
    tglMasuk = tglMasuk,
    idKategori = idKategori
)

fun Buku.toDetailBuku(): DetailBuku = DetailBuku(
    id = id,
    judulBuku = judulBuku,
    pengarang = pengarang,
    tglMasuk = tglMasuk,
    idKategori = idKategori
)

fun Buku.toUiStateBuku(isEntryValid: Boolean = false): UiStateBuku = UiStateBuku(
    detailBuku = this.toDetailBuku(),
    isEntryValid = isEntryValid
)