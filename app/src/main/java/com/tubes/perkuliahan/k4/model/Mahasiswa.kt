package com.tubes.perkuliahan.k4.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Date

@Entity
data class Mahasiswa(
    @PrimaryKey val id: String,
    val npm: String,
    val nama: String,
    val tanggal_lahir: Date ,
    val jenis_kelamin: String,
)

enum class JenisKelamin(var value: String) {
    PEREMPUAN("Perempuan"),
    LAKI_LAKI("Laki-laki")
}

