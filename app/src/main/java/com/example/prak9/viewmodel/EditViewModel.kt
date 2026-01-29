package com.example.prak9.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prak9.repo.RepoBuku
import com.example.prak9.repo.RepoKategori
import com.example.prak9.room.Kategori
import com.example.prak9.view.route.DestinasiEditBuku
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repoBuku: RepoBuku,
    private val repoKategori: RepoKategori
) : ViewModel() {

    var uiStateBuku by mutableStateOf(UiStateBuku())
        private set

    private val idBuku: Int = checkNotNull(savedStateHandle[DestinasiEditBuku.itemIdArg])

    val kategoriUiState: StateFlow<List<Kategori>> = repoKategori.getAllKategoriStream()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = listOf()
        )

    init {
        viewModelScope.launch {
            uiStateBuku = repoBuku.getBukuStream(idBuku)
                .filterNotNull()
                .first()
                .toUiStateBuku(true)
        }
    }

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

    suspend fun updateBuku() {
        if (validasiInput(uiStateBuku.detailBuku)) {
            repoBuku.updateBuku(uiStateBuku.detailBuku.toBuku())
        }
    }
}