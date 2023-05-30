package com.tubes.perkuliahan.k4.ui.screen.matakuliah

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
import com.tubes.perkuliahan.k4.model.MataKuliah
import kotlinx.coroutines.launch


@Composable
fun MataKuliahScreen(navController : NavHostController, modifier: Modifier = Modifier){
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val mataKuliahViewModel = hiltViewModel<MataKuliahViewModel>()
    val mataKuliahItems: List<MataKuliah> by mataKuliahViewModel.list.observeAsState(initial =
    listOf())

    LaunchedEffect(scope) {
        mataKuliahViewModel.loadItems()
    }

    mataKuliahViewModel.success.observe(LocalLifecycleOwner.current) {
        if (it) {
            scope.launch {
                mataKuliahViewModel.loadItems()
            }
        }
    }
}