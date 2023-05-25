package com.tubes.perkuliahan.k4.data.model

import androidx.room.PrimaryKey
import java.util.*

data class Mahasiswa(
    @PrimaryKey val id: String,
    val npm: String,
    val nama: String,
    val tanggalLahir: Date,
    val jenisKelamin: JenisKelamin
)

enum class JenisKelamin {
    LAKI_LAKI,
    PEREMPUAN
}
