package com.example.ucp2.view.route

import com.example.ucp2.R

object DestinasiDetail : DestinasiNavigasi {
    override val route = "detail_buku"
    override val titleRes = R.string.detail_buku
    const val itemIDArg = "idBuku"
    val routeWithArgs = "$route/{$itemIDArg}"
}