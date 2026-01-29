package com.example.ucp2.view.uicontroller

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2.view.DetailSiswaScreen
import com.example.ucp2.view.EditSiswaScreen
import com.example.ucp2.view.EntryBukuScreen
import com.example.ucp2.view.EntryKategoriScreen
import com.example.ucp2.view.HomeScreen
import com.example.ucp2.view.route.DestinasiDetail
import com.example.ucp2.view.route.DestinasiEditBuku
import com.example.ucp2.view.route.DestinasiEntry
import com.example.ucp2.view.route.DestinasiEntryKategori
import com.example.ucp2.view.route.DestinasiHome

@Composable
fun SiswaApp(navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {
    HostNavigasi(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {
        composable(route = DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(route = DestinasiEntry.route) },
                navigateToItemUpdate = {
                    navController.navigate("${DestinasiDetail.route}/$it")
                },
                navigateToKategori = {
                    navController.navigate(DestinasiEntryKategori.route)
                }
            )
        }
        composable(route = DestinasiEntry.route) {
            EntryBukuScreen(navigateBack = { navController.popBackStack() })
        }
        composable(route = DestinasiEntryKategori.route) {
            EntryKategoriScreen(navigateBack = { navController.popBackStack() })
        }
        composable(route = DestinasiDetail.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetail.itemIDArg) {
                type = NavType.IntType
            })
        ) {
            DetailSiswaScreen(
                navigateToEditItem = { id ->
                    navController.navigate("${DestinasiEditBuku.route}/$id")
                },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(route = DestinasiEditBuku.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEditBuku.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            EditSiswaScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}