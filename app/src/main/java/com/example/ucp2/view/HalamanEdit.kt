package com.example.ucp2.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.view.route.DestinasiEditBuku
import com.example.ucp2.viewmodel.EditViewModel
import com.example.ucp2.viewmodel.provider.PenyediaViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditSiswaScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    Scaffold(
        topBar = {
            SiswaTopAppBar(
                title = stringResource(DestinasiEditBuku.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        val coroutineScope = rememberCoroutineScope()
        val listKategori by viewModel.kategoriUiState.collectAsState()

        EntryBukuBody(
            uiStateBuku = viewModel.uiStateBuku,
            listKategori = listKategori,
            onBukuValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateBuku()
                    navigateBack()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}