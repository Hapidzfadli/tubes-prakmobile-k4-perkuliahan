package com.tubes.perkuliahan.k4.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Feature(
    val id: String,
    val title: String,
    @DrawableRes val iconId: Int,
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color,
    val jumlah: Int
)
