package com.tubes.perkuliahan.k4.ui.screen.matakuliah

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import com.tubes.perkuliahan.k4.model.MataKuliah
import kotlinx.coroutines.launch
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tubes.perkuliahan.k4.R
import com.tubes.perkuliahan.k4.ui.theme.*
import com.tubes.perkuliahan.k4.ui.utils.standardQuadFromTo
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun MataKuliahScreen(navController : NavHostController, modifier: Modifier = Modifier){
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val matakuliahViewModel = hiltViewModel<MataKuliahViewModel>()
    val matakuliahItems: List<MataKuliah> by matakuliahViewModel.list.observeAsState(initial =
    listOf())
    var id by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxSize()){
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
                painter = painterResource(id = R.drawable.plus),
                contentDescription = "Plus",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        navController.navigate("tambah-matakuliah")
                    }
            )
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()){
            items(items = matakuliahItems, itemContent = { item ->
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
                            painter = painterResource(id = R.drawable.book), // Ganti dengan resource avatar Anda
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
                    Row(modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)){

                        Image(
                            painter = painterResource(id = R.drawable.edit),
                            contentDescription = "Edit",
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    navController.navigate("edit-matakuliah/${item.id}")
                                }
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Image(
                            painter = painterResource(id = R.drawable.delete),
                            contentDescription = "Delete",
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    id = item.id
                                    showDialog = true
                                }
                        )
                        // Dialog

                    }
                }

            })
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(
                    text = "Konfirmasi",
                    style = TextStyle(
                        color = TextBlack,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                ) },
                text = { Text(
                    text = "Apakah Anda yakin ingin menghapus?",
                    style = TextStyle(
                        color = TextBlack,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                ) },
                confirmButton = {
                    Button(
                        onClick = {
                            scope.launch {
                                matakuliahViewModel.delete(id)
                            }
                            showDialog = false
                        }
                    ) {
                        Text(
                            text = "Ya",
                            style = TextStyle(
                                color = Color.White,
                                fontFamily = poppins,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            )
                        )
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            showDialog = false
                        }
                    ) {
                        Text(
                            text = "Tidak",
                            style = TextStyle(
                                color = Color.White,
                                fontFamily = poppins,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            )
                        )
                    }
                }
            )
        }
    }

    LaunchedEffect(scope) {
        matakuliahViewModel.loadItems()
    }

    matakuliahViewModel.success.observe(LocalLifecycleOwner.current) {
        if (it) {
            scope.launch {
                matakuliahViewModel.loadItems()
            }
        }
    }
}