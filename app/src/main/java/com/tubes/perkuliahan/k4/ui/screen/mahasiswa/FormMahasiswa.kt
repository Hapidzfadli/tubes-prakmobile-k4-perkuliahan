package com.tubes.perkuliahan.k4.ui.screen.mahasiswa

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
fun FormMahasiswa(navController: NavController, id: String? = null, modifier: Modifier = Modifier){
    val isLoading = remember { mutableStateOf(false) }
    val buttonLabel = if (isLoading.value) "Mohon tunggu..." else "Simpan"
    val viewModel = hiltViewModel<MahasiswaViewModel>()
    var npm = remember { mutableStateOf(TextFieldValue("")) }
    var nama = remember { mutableStateOf(TextFieldValue("")) }
    var tanggal_lahir = remember { mutableStateOf(TextFieldValue("")) }
    var jenis_kelamin by remember { mutableStateOf(JenisKelamin.PEREMPUAN) }
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
                painter = painterResource(id = R.drawable.man), // Ganti dengan resource avatar Anda
                contentDescription = "Avatar",
                modifier = Modifier.size(64.dp).align(Alignment.Center)
            )
        }
        Divider(color = TextWhite, modifier = Modifier.padding(vertical = 15.dp))
        LazyColumn (modifier = Modifier.fillMaxSize(). padding(bottom = 60.dp)) {
            item {
                OutlinedTextField(
                    label = { Text(text = "npm") },
                    value = npm.value,
                    onValueChange = {
                        npm.value = it
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
                    placeholder = { Text(text = "masukan nama mahasiswa") }
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
                    selectedValue = jenis_kelamin,
                    onValueSelected = { selectedJenisKelamin -> jenis_kelamin = selectedJenisKelamin }
                )
            }
            item {
                Button(modifier = Modifier.padding(10.dp), onClick = {
                    if (id == null) {
                        scope.launch {
                            viewModel.insert(
                                npm.value.text,
                                nama.value.text,
                                tanggal_lahir.value.text,
                                jenis_kelamin,
                            )
                        }
                    } else {
                        scope.launch {
                            viewModel.update(
                                id,
                                npm.value.text,
                                nama.value.text,
                                tanggal_lahir.value.text,
                                jenis_kelamin)
                        }
                    }
                    navController.navigate("mahasiswa")
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
            viewModel.loadItem(id) { mahasiswa ->
                mahasiswa?.let {
                    nidn.value = TextFieldValue(mahasiswa.nidn)
                    nama.value = TextFieldValue(mahasiswa.nama)
                    gelar_depan.value = TextFieldValue(mahasiswa.gelar_depan)
                    gelar_belakang.value = TextFieldValue(mahasiswa.gelar_belakang)
                    jenis_kelamin = mahasiswa.jenis_kelamin
                }
            }
        }
    }
}


@Composable
fun EnumRadioGroup(
    selectedValue: JenisKelamin,
    onValueSelected: (JenisKelamin) -> Unit
) {
    Row (modifier = Modifier.fillMaxWidth()) {
        JenisKelamin.values().forEach { JenisKelamin ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .clickable { onValueSelected(jenis_kelamin) }
            ) {
                RadioButton(
                    selected = jenis_kelamin == selectedValue,
                    onClick = { onValueSelected(jenis_kelamin) }
                )
                Text(
                    text = jenis_kelamin.name,
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
