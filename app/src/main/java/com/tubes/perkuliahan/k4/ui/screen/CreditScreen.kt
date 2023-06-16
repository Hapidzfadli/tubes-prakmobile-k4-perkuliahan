package com.tubes.perkuliahan.k4.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tubes.perkuliahan.k4.R
import com.tubes.perkuliahan.k4.ui.theme.TextBlack
import com.tubes.perkuliahan.k4.ui.theme.poppins

@Composable
fun CreditScren(navController : NavHostController, modifier: Modifier = Modifier){
    Column(modifier.padding(16.dp). fillMaxWidth()) {
        Text(
            text = "Developer",
            style = MaterialTheme.typography.h2
        )
        Row(modifier = Modifier.padding(15.dp).fillMaxWidth()){
            Image(
                painter = painterResource(id = R.drawable.github), // Ganti dengan resource avatar Anda
                contentDescription = "Avatar",
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = "github/Hapidzfadli",
                style = TextStyle(
                    color = TextBlack,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                ),
            )
        }
        Row(modifier = Modifier.padding(15.dp).fillMaxWidth()){
            Image(
                painter = painterResource(id = R.drawable.github), // Ganti dengan resource avatar Anda
                contentDescription = "Avatar",
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = "github/azikrasalma",
                style = TextStyle(
                    color = TextBlack,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                ),
            )
        }
        Row(modifier = Modifier.padding(15.dp).fillMaxWidth()){
            Image(
                painter = painterResource(id = R.drawable.github), // Ganti dengan resource avatar Anda
                contentDescription = "Avatar",
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = "github/bintangyandiii",
                style = TextStyle(
                    color = TextBlack,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                ),
            )
        }
        Row(modifier = Modifier.padding(15.dp).fillMaxWidth()){
            Image(
                painter = painterResource(id = R.drawable.github), // Ganti dengan resource avatar Anda
                contentDescription = "Avatar",
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = "github/ihsandwi17",
                style = TextStyle(
                    color = TextBlack,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                ),
            )
        }
        Row(modifier = Modifier.padding(15.dp).fillMaxWidth()){
            Image(
                painter = painterResource(id = R.drawable.github), // Ganti dengan resource avatar Anda
                contentDescription = "Avatar",
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = "github/sulisrosliani04",
                style = TextStyle(
                    color = TextBlack,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                ),
            )
        }
    }
}