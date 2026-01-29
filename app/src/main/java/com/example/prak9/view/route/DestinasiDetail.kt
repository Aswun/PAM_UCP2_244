package com.example.prak9.view.route

import com.example.prak9.R

object DestinasiDetail : DestinasiNavigasi {
    override val route = "detail_buku"
    override val titleRes = R.string.detail_buku
    const val itemIDArg = "idBuku"
    val routeWithArgs = "$route/{$itemIDArg}"
}