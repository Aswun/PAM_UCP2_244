package com.example.ucp2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ucp2.repo.RepoKategori
import com.example.ucp2.room.Kategori

class EntryKategoriViewModel(private val repoKategori: RepoKategori) : ViewModel() {

    var uiStateKategori by mutableStateOf(EntryKategoriUiState())
        private set

    fun updateUiState(detailKategori: DetailKategori) {
        uiStateKategori = EntryKategoriUiState(
            detailKategori = detailKategori,
            isEntryValid = validasiInput(detailKategori)
        )
    }

    private fun validasiInput(uiState: DetailKategori = uiStateKategori.detailKategori): Boolean {
        return uiState.nama.isNotBlank()
    }

    suspend fun saveKategori() {
        if (validasiInput()) {
            repoKategori.insertKategori(uiStateKategori.detailKategori.toKategori())
        }
    }
}

data class EntryKategoriUiState(
    val detailKategori: DetailKategori = DetailKategori(),
    val isEntryValid: Boolean = false
)

data class DetailKategori(
    val id: Int = 0,
    val nama: String = ""
)

fun DetailKategori.toKategori(): Kategori = Kategori(
    id = id,
    nama = nama
)

fun Kategori.toDetailKategori(): DetailKategori = DetailKategori(
    id = id,
    nama = nama
)