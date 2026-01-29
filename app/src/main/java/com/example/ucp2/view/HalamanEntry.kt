package com.example.ucp2.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.room.Kategori
import com.example.ucp2.view.route.DestinasiEntry
import com.example.ucp2.viewmodel.DetailBuku
import com.example.ucp2.viewmodel.EntryViewModel
import com.example.ucp2.viewmodel.provider.PenyediaViewModel
import kotlinx.coroutines.launch
import com.example.ucp2.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryBukuScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EntryViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val kategoriList by viewModel.kategoriUiState.collectAsState()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SiswaTopAppBar(
                title = stringResource(DestinasiEntry.titleRes),
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryBukuBody(
            uiStateBuku = viewModel.uiStateBuku,
            listKategori = kategoriList,
            onBukuValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveBuku()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(paddingValues = innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryBukuBody(
    uiStateBuku: com.example.ucp2.viewmodel.UiStateBuku,
    listKategori: List<Kategori>,
    onBukuValueChange: (DetailBuku) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(R.dimen.padding_medium))
    ) {
        FormInputBuku(
            detailBuku = uiStateBuku.detailBuku,
            listKategori = listKategori,
            onValueChange = onBukuValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = uiStateBuku.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.btn_submit))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputBuku(
    detailBuku: DetailBuku,
    listKategori: List<Kategori>,
    onValueChange: (DetailBuku) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedKategoriNama by remember { mutableStateOf(detailBuku.namaKategori) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
    ) {
        OutlinedTextField(
            value = detailBuku.judulBuku,
            onValueChange = { onValueChange(detailBuku.copy(judulBuku = it)) },
            label = { Text(stringResource(R.string.nama_buku)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailBuku.pengarang,
            onValueChange = { onValueChange(detailBuku.copy(pengarang = it)) },
            label = { Text(stringResource(R.string.pengarang)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailBuku.tglMasuk,
            onValueChange = { onValueChange(detailBuku.copy(tglMasuk = it)) },
            label = { Text(stringResource(R.string.tanggal_masuk)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                readOnly = true,
                value = selectedKategoriNama,
                onValueChange = {},
                label = { Text(stringResource(R.string.kategori_buku)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                listKategori.forEach { kategori ->
                    DropdownMenuItem(
                        text = { Text(text = kategori.nama) },
                        onClick = {
                            selectedKategoriNama = kategori.nama
                            onValueChange(detailBuku.copy(idKategori = kategori.id))
                            expanded = false
                        }
                    )
                }
            }
        }
        if (enabled) {
            Text(
                text = stringResource(R.string.required_field),
                modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium))
            )
        }
    }
}