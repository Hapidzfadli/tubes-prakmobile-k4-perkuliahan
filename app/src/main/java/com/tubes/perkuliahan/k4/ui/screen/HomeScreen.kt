package com.tubes.perkuliahan.k4.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tubes.perkuliahan.k4.R
import com.tubes.perkuliahan.k4.data.model.Dosen
import com.tubes.perkuliahan.k4.model.Mahasiswa
import com.tubes.perkuliahan.k4.model.Feature
import com.tubes.perkuliahan.k4.model.MataKuliah
import com.tubes.perkuliahan.k4.ui.screen.dosen.DosenViewModel
import com.tubes.perkuliahan.k4.ui.screen.mahasiswa.MahasiswaViewModel
import com.tubes.perkuliahan.k4.ui.screen.matakuliah.MataKuliahViewModel
import com.tubes.perkuliahan.k4.ui.theme.*
import com.tubes.perkuliahan.k4.ui.utils.standardQuadFromTo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController : NavHostController, modifier: Modifier = Modifier) {
    val dosenScope = rememberCoroutineScope()
    val mahasiswaScope = rememberCoroutineScope()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val dosenViewModel = hiltViewModel<DosenViewModel>()
    val dosenItems: List<Dosen> by dosenViewModel.list.observeAsState(initial =
    listOf())

    val mahasiswaViewModel = hiltViewModel<MahasiswaViewModel>()
    val mahasiswaItems: List<Mahasiswa> by mahasiswaViewModel.list.observeAsState(initial =
    listOf())

    val mataKuliahViewModel = hiltViewModel<MataKuliahViewModel>()
    val mataKuliahItems: List<MataKuliah> by mataKuliahViewModel.list.observeAsState(initial =
    listOf())

    val perkuliahan : Int = (dosenItems?.size ?: 0) + (mahasiswaItems?.size ?: 0) + (mataKuliahItems?.size ?: 0)
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Column {
            GreetingSection ()
            CurrentDateTime()
            FeatureSection(
                features = listOf(
                    Feature(
                        title = "Dosen",
                        R.drawable.teacher,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3,
                        dosenItems?.size ?: 0
                    ),
                    Feature(
                        title = "Mahasiswa",
                        R.drawable.graduated,
                        LightGreen1,
                        LightGreen2,
                        LightGreen3,
                        mahasiswaItems?.size ?: 0
                    ),
                    Feature(
                        title = "Mata Kuliah",
                        R.drawable.matkul,
                        OrangeYellow1,
                        OrangeYellow2,
                        OrangeYellow3,
                        mataKuliahItems?.size ?: 0
                    ),
                    Feature(
                        title = "Total",
                        R.drawable.symbol,
                        Beige1,
                        Beige2,
                        Beige3,
                        perkuliahan
                    )
                )
            )
        }
    }

    LaunchedEffect(dosenScope) {
        dosenViewModel.loadItems()

    }
    LaunchedEffect(mahasiswaScope) {
        mahasiswaViewModel.loadItems()
    }
    LaunchedEffect(scope) {
        mataKuliahViewModel.loadItems()
    }

    dosenViewModel.success.observe(LocalLifecycleOwner.current) {
        if (it) {
            dosenScope.launch {
                dosenViewModel.loadItems()
            }
        }
    }
    mahasiswaViewModel.success.observe(LocalLifecycleOwner.current) {
        if (it) {
            mahasiswaScope.launch {
                mahasiswaViewModel.loadItems()
            }
        }
    }
    mataKuliahViewModel.success.observe(LocalLifecycleOwner.current) {
        if (it) {
            scope.launch {
                mataKuliahViewModel.loadItems()
            }
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

@ExperimentalFoundationApi
@Composable
fun FeatureSection(features: List<Feature>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Dashboard",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(15.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(features.size) {
                FeatureItem(feature = features[it])
            }
        }
    }
}

@Composable
fun FeatureItem(
    feature: Feature
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        // Medium colored path
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        // Light colored path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            drawPath(
                path = mediumColoredPath,
                color = feature.mediumColor
            )
            drawPath(
                path = lightColoredPath,
                color = feature.lightColor
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = feature.title,
                style = TextStyle(
                    color = Color.White,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Icon(
                painter = painterResource(id = feature.iconId),
                contentDescription = feature.title,
                tint = Color.White,

                modifier = Modifier.align(Alignment.BottomStart).size(32.dp)
            )
            Text(
                text = "view all",
                style = TextStyle(
                    color = Color.White,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp
                ),
                modifier = Modifier
                    .clickable {
                        // Handle the click
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 4.dp, horizontal = 10.dp)
            )
            Text(
                text = feature.jumlah.toString(),
                style = TextStyle(
                    color = Color.White,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                modifier = Modifier
                    .align(Alignment.Center)

            )
        }
    }
}