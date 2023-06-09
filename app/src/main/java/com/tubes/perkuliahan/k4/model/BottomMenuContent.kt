package com.tubes.perkuliahan.k4.model

import androidx.annotation.DrawableRes

data class BottomMenuContent(
    val id: String,
    val title: String,
    @DrawableRes val iconId: Int
)