package com.tubes.perkuliahan.k4.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.tubes.perkuliahan.k4.model.MenuItem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tubes.perkuliahan.k4.R
import com.tubes.perkuliahan.k4.ui.theme.*

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BlueDate),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 40.dp, horizontal = 20.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.man), // Ganti dengan resource avatar Anda
                contentDescription = "Avatar",
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "User Guest", // Ganti dengan nama Anda
                style =  TextStyle(
                    color = Color.White,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                ),
            )
        }
    }
}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(modifier) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Icon(
                    painter = item.icon,
                    contentDescription = item.contentDescription,
                    modifier = Modifier.size(24.dp),
                    tint = DeepBlue,
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.title,
                    style = TextStyle(
                        color = TextBlack,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}