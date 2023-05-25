package com.tubes.perkuliahan.k4.data.model

import java.util.*

data class Mahasiswa(
    val npm: String,
    val nama: String,
    val tanggalLahir: Date,
    val jenisKelamin: JenisKelamin
)

enum class JenisKelamin {
    LAKI_LAKI,
    PEREMPUAN
}
