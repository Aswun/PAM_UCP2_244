package com.example.ucp2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.repo.RepoBuku
import com.example.ucp2.repo.RepoKategori
import com.example.ucp2.room.Buku
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted

/**
class HomeViewModel(private val repoSiswa: RepoSiswa) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUiState: StateFlow<HomeUiState> =
        repoSiswa.getAllSiswaStream()
            .filterNotNull()
            .map {
                HomeUiState(listSiswa = it.toList())
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )
}

data class HomeUiState(
    val listSiswa: List<Siswa> = listOf()
)
**/

class HomeViewModel(private val repoKategori: RepoKategori, private val repoBuku: RepoBuku) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUiState: StateFlow<HomeUiState> =
        repoBuku.getAllBukuStream()
            .filterNotNull()
            .map {
                HomeUiState(listBuku = it.toList())
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )
}

data class HomeUiState(
    val listBuku: List<Buku> = listOf()
)