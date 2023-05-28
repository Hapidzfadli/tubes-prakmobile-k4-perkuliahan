package com.tubes.perkuliahan.k4.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tubes.perkuliahan.k4.R
import com.tubes.perkuliahan.k4.ui.theme.DeepBlue
import com.tubes.perkuliahan.k4.ui.theme.TextWhite
import com.tubes.perkuliahan.k4.ui.theme.poppins
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen(navController : NavHostController, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Column {
            GreetingSection ()
            CurrentDateTime()
        }
    }
}

@Composable
fun GreetingSection () {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Hai Teman",
                style = MaterialTheme.typography.h2
            )
            Text(
                text = "Semoga harimu menyenangkan!",
                style = MaterialTheme.typography.body1
            )
        }
    }
}


@Composable
fun CurrentDateTime() {
    val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

    var currentDateTime by remember { mutableStateOf(Calendar.getInstance().time) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp)
            .background(Color(0xFF00C4FF), RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .clickable {
            }
            .clip(RoundedCornerShape(10.dp))
    ) {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 15.dp, top = 10.dp, bottom = 10.dp)
        ) {
            Icon(
                Icons.Default.DateRange,
                contentDescription = "Date Icon",
                modifier = Modifier.size(24.dp),
                tint = Color.White,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${dateFormat.format(currentDateTime)} : ${timeFormat.format(currentDateTime)}",
                style = TextStyle(
                    color = Color.White,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(top = 3.dp)
            )
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000) // Delay for 1 second
            currentDateTime = Calendar.getInstance().time // Update currentDateTime
        }
    }
}