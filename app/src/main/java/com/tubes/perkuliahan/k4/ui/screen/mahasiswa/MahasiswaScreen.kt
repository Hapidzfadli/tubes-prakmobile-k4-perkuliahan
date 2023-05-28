package com.tubes.perkuliahan.k4.ui.screen.mahasiswa

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tubes.perkuliahan.k4.model.Mahasiswa
import kotlinx.coroutines.launch


@Composable
fun MahasiswaScreen(navController : NavHostController, modifier: Modifier = Modifier){
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val mahasiswaViewModel = hiltViewModel<MahasiswaViewModel>()
    val mahasiswaItems: List<Mahasiswa> by mahasiswaViewModel.list.observeAsState(initial =
    listOf())

    LaunchedEffect(scope) {
        mahasiswaViewModel.loadItems()
    }

    mahasiswaViewModel.success.observe(LocalLifecycleOwner.current) {
        if (it) {
            scope.launch {
                mahasiswaViewModel.loadItems()
            }
        }
    }
}