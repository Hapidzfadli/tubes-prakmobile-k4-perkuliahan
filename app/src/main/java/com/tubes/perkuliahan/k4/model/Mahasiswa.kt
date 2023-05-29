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
    @ColumnInfo(name = "jenis_kelamin", defaultValue = "LAKI_LAKI") var jenis_kelamin: JenisKelamin,
)

enum class JenisKelamin {
    LAKI_LAKI,
    PEREMPUAN
}
