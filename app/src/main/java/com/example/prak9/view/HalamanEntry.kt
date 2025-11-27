package com.example.prak9.view

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntrySiswaApp(
    navigationBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EntrySiswaViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {}