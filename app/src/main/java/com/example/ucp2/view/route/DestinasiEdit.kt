package com.example.ucp2.view.route

import com.example.ucp2.R

object DestinasiEditBuku : DestinasiNavigasi {
    override val route = "edit_buku"
    override val titleRes = R.string.edit_buku
    const val itemIdArg = "idBuku"
    val routeWithArgs = "$route/{$itemIdArg}"
}