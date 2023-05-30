package com.tubes.perkuliahan.k4.ui.utils



import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tubes.perkuliahan.k4.R
import com.tubes.perkuliahan.k4.ui.theme.TextBlack
import com.tubes.perkuliahan.k4.ui.theme.poppins

@Composable
fun AppBar(
    title : String,
    onNavigationIconClick: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.height(56.dp),
        elevation = 0.dp,
        backgroundColor = Color.White,

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    painter = painterResource(id = R.drawable.menu),
                    contentDescription = "Menu",
                    tint = TextBlack,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.weight(0.6f))
            Text(
                text = title,
                style = TextStyle(
                    color = TextBlack,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterVertically).padding(top = 3.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
