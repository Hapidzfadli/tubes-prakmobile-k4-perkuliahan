package com.tubes.perkuliahan.k4.ui.screen.dosen

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
import com.tubes.perkuliahan.k4.data.model.Dosen
import kotlinx.coroutines.launch

@Composable
fun DosenScreen(navController : NavHostController, modifier: Modifier = Modifier){
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val dosenViewModel = hiltViewModel<DosenViewModel>()
    val dosenItems: List<Dosen> by dosenViewModel.list.observeAsState(initial =
    listOf())

    LaunchedEffect(scope) {
        dosenViewModel.loadItems()
    }

    dosenViewModel.success.observe(LocalLifecycleOwner.current) {
        if (it) {
            scope.launch {
                dosenViewModel.loadItems()
            }
        }
    }
}