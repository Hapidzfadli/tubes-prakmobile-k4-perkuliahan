package com.tubes.perkuliahan.k4.data.model

data class Dosen(
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
