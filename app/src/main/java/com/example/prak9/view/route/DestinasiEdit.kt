package com.example.prak9.view.route

import com.example.prak9.R

object DestinasiEditBuku : DestinasiNavigasi {
    override val route = "edit_buku"
    override val titleRes = R.string.edit_buku
    const val itemIdArg = "idBuku"
    val routeWithArgs = "$route/{$itemIdArg}"
}