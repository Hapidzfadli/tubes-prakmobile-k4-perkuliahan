package com.tubes.perkuliahan.k4.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.runtime.livedata.observeAsState
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
import com.tubes.perkuliahan.k4.data.model.Dosen
import com.tubes.perkuliahan.k4.model.Mahasiswa
import com.tubes.perkuliahan.k4.model.MataKuliah
import com.tubes.perkuliahan.k4.ui.screen.dosen.DosenViewModel
import com.tubes.perkuliahan.k4.ui.screen.mahasiswa.MahasiswaViewModel
import com.tubes.perkuliahan.k4.ui.screen.matakuliah.MataKuliahViewModel
import com.tubes.perkuliahan.k4.ui.theme.*
import com.tubes.perkuliahan.k4.ui.utils.standardQuadFromTo
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AllScreen(navController : NavHostController, modifier: Modifier = Modifier){
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var id by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    val dosenViewModel = hiltViewModel<DosenViewModel>()
    val dosenItems: List<Dosen> by dosenViewModel.list.observeAsState(initial =
    listOf())

    val mahasiswaViewModel = hiltViewModel<MahasiswaViewModel>()
    val mahasiswaItems: List<Mahasiswa> by mahasiswaViewModel.list.observeAsState(initial =
    listOf())

    val mataKuliahViewModel = hiltViewModel<MataKuliahViewModel>()
    val mataKuliahItems: List<MataKuliah> by mataKuliahViewModel.list.observeAsState(initial =
    listOf())

    val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))

    Column(modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState())){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(
                text = "Daftar Dosen",
                style = MaterialTheme.typography.h2
            )
            Image(
                painter = painterResource(id = com.tubes.perkuliahan.k4.R.drawable.eye),
                contentDescription = "view",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        navController.navigate("dosen")
                    }
            )
        }
        LazyColumn(modifier = Modifier.fillMaxWidth().aspectRatio(3f)){
            items(items = dosenItems, itemContent = { item ->
                BoxWithConstraints(
                    modifier = Modifier
                        .padding(7.5.dp)
                        .aspectRatio(3.5f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(BlueViolet3)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("edit-dosen/${item.id}")
                        }
                ){
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
                            .fillMaxWidth()
                    ) {
                        drawPath(
                            path = mediumColoredPath,
                            color = BlueViolet2
                        )
                        drawPath(
                            path = lightColoredPath,
                            color = BlueViolet1
                        )
                    }
                    Row(modifier = Modifier.padding(15.dp)){
                        Image(
                            painter = painterResource(id = com.tubes.perkuliahan.k4.R.drawable.dosen), // Ganti dengan resource avatar Anda
                            contentDescription = "Avatar",
                            modifier = Modifier.size(64.dp)
                        )
                        Column(modifier = Modifier.padding(horizontal = 15.dp)){
                            Text(
                                text = item.gelar_depan + ". " + item.nama + ". " + item.gelar_belakang,
                                style = TextStyle(
                                    color = Color.White,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                ),
                            )
                            Text(
                                text = item.nidn,
                                style = TextStyle(
                                    color = TextBlack,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                ),
                            )
                            Text(
                                text = item.pendidikan.toString(),
                                style = TextStyle(
                                    color = TextBlack,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                ),
                            )
                        }
                    }
                }

            })
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(
                text = "Daftar Mahasiswa",
                style = MaterialTheme.typography.h2
            )
            Image(
                painter = painterResource(id = com.tubes.perkuliahan.k4.R.drawable.eye),
                contentDescription = "view",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        navController.navigate("mahasiswa")
                    }
            )
        }
        LazyColumn(modifier = Modifier.fillMaxWidth().aspectRatio(3f)){
            items(items = mahasiswaItems, itemContent = { item ->
                BoxWithConstraints(
                    modifier = Modifier
                        .padding(7.5.dp)
                        .aspectRatio(3.5f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Beige3)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("edit-mahasiswa/${item.id}")
                        }
                ){
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
                            .fillMaxWidth()
                    ) {
                        drawPath(
                            path = mediumColoredPath,
                            color = Beige2
                        )
                        drawPath(
                            path = lightColoredPath,
                            color = Beige1
                        )
                    }
                    Row(modifier = Modifier.padding(15.dp)){

                        if(item.jenis_kelamin == "Perempuan") {
                            Image(
                                painter = painterResource(id = com.tubes.perkuliahan.k4.R.drawable.woman), // Ganti dengan resource avatar Anda
                                contentDescription = "Avatar",
                                modifier = Modifier.size(64.dp)
                            )
                        } else if (item.jenis_kelamin == "Laki-laki"){
                            Image(
                                painter = painterResource(id = com.tubes.perkuliahan.k4.R.drawable.man), // Ganti dengan resource avatar Anda
                                contentDescription = "Avatar",
                                modifier = Modifier.size(64.dp)
                            )
                        }
                        Column(modifier = Modifier.padding(horizontal = 15.dp)){
                            Text(
                                text = item.nama,
                                style = TextStyle(
                                    color = Color.White,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                ),
                            )
                            Text(
                                text = item.npm,
                                style = TextStyle(
                                    color = TextBlack,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                ),
                            )
                            Text(
                                text = item.jenis_kelamin.toString(),
                                style = TextStyle(
                                    color = TextBlack,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                ),
                            )
                            Text(
                                text = dateFormat.format(item.tanggal_lahir),
                                style = TextStyle(
                                    color = TextBlack,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                ),
                            )
                        }
                    }
                }

            })
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(
                text = "Daftar Mata Kuliah",
                style = MaterialTheme.typography.h2
            )
            Image(
                painter = painterResource(id = com.tubes.perkuliahan.k4.R.drawable.eye),
                contentDescription = "View",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        navController.navigate("matkul")
                    }
            )
        }
        LazyColumn(modifier = Modifier.fillMaxWidth().aspectRatio(3f)){
            items(items = mataKuliahItems, itemContent = { item ->
                BoxWithConstraints(
                    modifier = Modifier
                        .padding(7.5.dp)
                        .aspectRatio(3.5f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = if (item.praktikum == 1) LightGreen3 else OrangeYellow1)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("edit-matakuliah/${item.id}")
                        }
                ){
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
                            .fillMaxWidth()
                    ) {
                        drawPath(
                            path = mediumColoredPath,
                            color = if (item.praktikum == 1) LightGreen2 else OrangeYellow2
                        )
                        drawPath(
                            path = lightColoredPath,
                            color = if (item.praktikum == 1) LightGreen1 else OrangeYellow3
                        )
                    }
                    Row(modifier = Modifier.padding(15.dp)){

                        Image(
                            painter = painterResource(id = com.tubes.perkuliahan.k4.R.drawable.book), // Ganti dengan resource avatar Anda
                            contentDescription = "Avatar",
                            modifier = Modifier.size(64.dp)
                        )
                        Column(modifier = Modifier.padding(horizontal = 15.dp)){
                            Text(
                                text = item.nama,
                                style = TextStyle(
                                    color = Color.White,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                ),
                            )
                            Text(
                                text = item.kode,
                                style = TextStyle(
                                    color = TextBlack,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                ),
                            )
                            Text(
                                text = "${item.sks} sks",
                                style = TextStyle(
                                    color = TextBlack,
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                ),
                            )
                        }
                    }
                }

            })
        }
    }


    LaunchedEffect(scope) {
        mahasiswaViewModel.loadItems()
        dosenViewModel.loadItems()
        mataKuliahViewModel.loadItems()
    }

    dosenViewModel.success.observe(LocalLifecycleOwner.current) {
        if (it) {
            scope.launch {
                dosenViewModel.loadItems()
            }
        }
    }
    mahasiswaViewModel.success.observe(LocalLifecycleOwner.current) {
        if (it) {
            scope.launch {
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