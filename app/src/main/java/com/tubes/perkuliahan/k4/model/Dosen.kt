package com.tubes.perkuliahan.k4.data.model

import androidx.room.PrimaryKey

data class Dosen(
    @PrimaryKey val id: String,
    val nidn: String,
    val nama: String,
    val gelarDepan: String,
    val gelarBelakang: String,
    val pendidikan: Pendidikan
)

enum class Pendidikan {
    S2,
    S3
}
