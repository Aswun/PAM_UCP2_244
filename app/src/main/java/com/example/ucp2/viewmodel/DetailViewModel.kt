package com.example.ucp2.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.repo.RepoBuku
import com.example.ucp2.view.route.DestinasiDetail
import kotlinx.coroutines.flow.*

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repoBuku: RepoBuku
) : ViewModel() {

    private val idBuku: Int = checkNotNull(savedStateHandle[DestinasiDetail.itemIDArg])

    val uiDetailState: StateFlow<DetailBukuUiState> =
        repoBuku.getBukuStream(idBuku)
            .filterNotNull()
            .map {
                DetailBukuUiState(detailBuku = it.toDetailBuku())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailBukuUiState()
            )

    suspend fun deleteBuku() {
        repoBuku.deleteBuku(uiDetailState.value.detailBuku.toBuku())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * UI state untuk Detail Buku
 */
data class DetailBukuUiState(
    val detailBuku: DetailBuku = DetailBuku()
)