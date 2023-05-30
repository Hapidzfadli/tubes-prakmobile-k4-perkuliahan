package com.tubes.perkuliahan.k4.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dosen(
    @PrimaryKey val id: String,
    val nidn: String,
    val nama: String,
    val gelar_depan: String,
    val gelar_belakang: String,
    val pendidikan: Pendidikan
)

enum class Pendidikan {
    S2,
    S3
}
