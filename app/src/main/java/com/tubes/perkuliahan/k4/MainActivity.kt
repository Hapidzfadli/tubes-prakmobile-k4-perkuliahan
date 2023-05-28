package com.tubes.perkuliahan.k4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tubes.perkuliahan.k4.data.model.Dosen
import com.tubes.perkuliahan.k4.ui.screen.HomeScreen
import com.tubes.perkuliahan.k4.ui.screen.MainScreen
import com.tubes.perkuliahan.k4.ui.theme.PerkuliahanTheme
import com.tubes.perkuliahan.k4.ui.screen.dosen.DosenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PerkuliahanTheme {
                // A surface container using the 'background' color from the theme
                MainScreen()
            }
        }
    }
}
