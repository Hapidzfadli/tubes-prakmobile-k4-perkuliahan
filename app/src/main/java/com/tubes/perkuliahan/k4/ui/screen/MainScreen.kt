package com.tubes.perkuliahan.k4.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tubes.perkuliahan.k4.R
import com.tubes.perkuliahan.k4.model.BottomMenuContent
import com.tubes.perkuliahan.k4.model.MenuItem
import com.tubes.perkuliahan.k4.ui.utils.AppBar
import com.tubes.perkuliahan.k4.ui.utils.BottomMenu
import com.tubes.perkuliahan.k4.ui.utils.DrawerBody
import com.tubes.perkuliahan.k4.ui.utils.DrawerHeader
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import com.tubes.perkuliahan.k4.ui.screen.dosen.DosenScreen
import com.tubes.perkuliahan.k4.ui.screen.mahasiswa.MahasiswaScreen
import com.tubes.perkuliahan.k4.ui.screen.matakuliah.MataKuliahScreen

@Composable
fun MainScreen () {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },

        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                items = listOf(
                    MenuItem(
                        id = "home",
                        title = "Home",
                        contentDescription = "Go to home screen",
                        icon = painterResource(id = R.drawable.home)
                    ),
                    MenuItem(
                        id = "mahasiswa",
                        title = "Mahasiswa",
                        contentDescription = "Go to mahasiswa screen",
                        icon = painterResource(id = R.drawable.graduated)
                    ),
                    MenuItem(
                        id = "dosen",
                        title = "Dosen",
                        contentDescription = "Go to dosen screen",
                        icon = painterResource(id = R.drawable.teacher)
                    ),
                    MenuItem(
                        id = "matakuliah",
                        title = "Mata Kuliah",
                        contentDescription = "Go to matakuliah screen",
                        icon = painterResource(id = R.drawable.matkul)
                    ),
                    MenuItem(
                        id = "credit",
                        title = "Credit",
                        contentDescription = "Go to credit screen",
                        icon = painterResource(id = R.drawable.copywriting)
                    ),

                ),
                onItemClick = {
                    val currentRoute = navController.currentBackStackEntry?.destination?.route
                    if (currentRoute != it.id) {
                        navController.navigate(it.id)
                    }
                }
            )
        },
        bottomBar = {
            BottomMenu(items = listOf(
                BottomMenuContent("home","Home", R.drawable.home),
                BottomMenuContent("dosen","Dosen", R.drawable.teacher),
                BottomMenuContent("mahasiswa","Mahasiswa", R.drawable.graduated),
                BottomMenuContent("matkul","Matkul", R.drawable.matkul),

            ), navController = navController)
        }
    )
    { innerPadding ->
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeScreen(navController =
                navController, modifier =
                Modifier.padding(innerPadding))
            }
            composable("dosen") {
                DosenScreen(navController = navController, Modifier.padding(innerPadding))
            }
            composable("mahasiswa") {
                MahasiswaScreen(navController = navController, Modifier.padding(innerPadding))
            }
            composable("matkul") {
                MataKuliahScreen(navController = navController, Modifier.padding(innerPadding))
            }
            composable("all") {
                AllScreen()
            }
            composable("credit") {
                CreditScren()
            }
        }

    }

    }
}