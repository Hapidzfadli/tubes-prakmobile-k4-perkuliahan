package com.tubes.perkuliahan.k4.ui.screen.dosen

import android.widget.RadioGroup
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tubes.perkuliahan.k4.R
import com.tubes.perkuliahan.k4.data.model.Pendidikan
import com.tubes.perkuliahan.k4.ui.theme.*
import com.tubes.perkuliahan.k4.ui.utils.standardQuadFromTo
import kotlinx.coroutines.launch

@Composable
fun FormDosen(navController: NavController, id: String? = null, modifier: Modifier = Modifier){
    val isLoading = remember { mutableStateOf(false) }
    val buttonLabel = if (isLoading.value) "Mohon tunggu..." else "Simpan"
    val viewModel = hiltViewModel<DosenViewModel>()
    var nidn = remember { mutableStateOf(TextFieldValue("")) }
    var nama = remember { mutableStateOf(TextFieldValue("")) }
    var gelar_depan = remember { mutableStateOf(TextFieldValue("")) }
    var gelar_belakang = remember { mutableStateOf(TextFieldValue("")) }
    var pendidikan by remember { mutableStateOf(Pendidikan.S2) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxWidth().padding(15.dp)
    ){
        BoxWithConstraints(
            modifier = Modifier
                .aspectRatio(2.5f)
                .clip(RoundedCornerShape(10.dp))
                .background(BlueViolet3)
                .fillMaxWidth()
                .clickable {   }
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
            Image(
                painter = painterResource(id = R.drawable.dosen), // Ganti dengan resource avatar Anda
                contentDescription = "Avatar",
                modifier = Modifier.size(64.dp).align(Alignment.Center)
            )
        }
        Divider(color = TextWhite, modifier = Modifier.padding(vertical = 15.dp))
        LazyColumn (modifier = Modifier.fillMaxSize(). padding(bottom = 60.dp)) {
            item {
                OutlinedTextField(
                    label = { Text(text = "Nidn") },
                    value = nidn.value,
                    onValueChange = {
                        nidn.value = it
                    },
                    modifier = Modifier.padding(4.dp).fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        capitalization =
                        KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text
                    ),
                    placeholder = { Text(text = "201313xx") }
                )
            }
            item {
                OutlinedTextField(
                    label = { Text(text = "Nama") },
                    value = nama.value,
                    onValueChange = {
                        nama.value = it
                    },
                    modifier = Modifier.padding(4.dp).fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        capitalization =
                        KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text
                    ),
                    placeholder = { Text(text = "masukan nama dosen") }
                )
            }
            item {
                OutlinedTextField(
                    label = { Text(text = "Gelar Depan") },
                    value = gelar_depan.value,
                    onValueChange = {
                        gelar_depan.value = it
                    },
                    modifier = Modifier.padding(4.dp).fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        capitalization =
                        KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text
                    ),
                    placeholder = { Text(text = "gelar depan") }
                )
            }
            item {
                OutlinedTextField(
                    label = { Text(text = "Gelar Belakang") },
                    value = gelar_belakang.value,
                    onValueChange = {
                        gelar_belakang.value = it
                    },
                    modifier = Modifier.padding(4.dp).fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        capitalization =
                        KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text
                    ),
                    placeholder = { Text(text = "gelar belakang") }
                )
            }
            item {
                EnumRadioGroup(
                    selectedValue = pendidikan,
                    onValueSelected = { selectedPendidikan -> pendidikan = selectedPendidikan }
                )
            }
            item {
                Button(modifier = Modifier.padding(10.dp), onClick = {
                    if (id == null) {
                        scope.launch {
                            viewModel.insert(
                                nidn.value.text,
                                nama.value.text,
                                gelar_depan.value.text,
                                gelar_belakang.value.text,
                                pendidikan,
                            )
                        }
                    } else {
                        scope.launch {
                            viewModel.update(
                                id,
                                nidn.value.text,
                                nama.value.text,
                                gelar_depan.value.text,
                                gelar_belakang.value.text, pendidikan)
                        }
                    }
                    navController.navigate("dosen")
                }) {
                    Text(
                        text = "Save Data",
                        style = TextStyle(
                            color = Color.White,
                            fontFamily = poppins,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        ),
                    )
                }
            }
        }

    }

    viewModel.isLoading.observe(LocalLifecycleOwner.current) {
        isLoading.value = it
    }

    if (id != null) {
        LaunchedEffect(scope) {
            viewModel.loadItem(id) { dosen ->
                dosen?.let {
                    nidn.value = TextFieldValue(dosen.nidn)
                    nama.value = TextFieldValue(dosen.nama)
                    gelar_depan.value = TextFieldValue(dosen.gelar_depan)
                    gelar_belakang.value = TextFieldValue(dosen.gelar_belakang)
                    pendidikan = dosen.pendidikan
                }
            }
        }
    }
}



@Composable
fun EnumRadioGroup(
    selectedValue: Pendidikan,
    onValueSelected: (Pendidikan) -> Unit
) {
    Row (modifier = Modifier.fillMaxWidth()) {
        Pendidikan.values().forEach { pendidikan ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .clickable { onValueSelected(pendidikan) }
            ) {
                RadioButton(
                    selected = pendidikan == selectedValue,
                    onClick = { onValueSelected(pendidikan) }
                )
                Text(
                    text = pendidikan.name,
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}
