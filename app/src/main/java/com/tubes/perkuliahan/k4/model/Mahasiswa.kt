package com.tubes.perkuliahan.k4.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Mahasiswa(
    @PrimaryKey val id: String,
    val npm: String,
    val nama: String,
    var tanggal_lahir: String,
    val jenis_kelamin: String,
)

enum class JenisKelamin(var value: String) {
    PEREMPUAN("Perempuan"),
    LAKI_LAKI("Laki-laki")
}

