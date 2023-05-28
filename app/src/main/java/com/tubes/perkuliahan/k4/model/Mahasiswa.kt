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
    @ColumnInfo(name = "tanggalLahir", defaultValue = "20020121") val tanggalLahir: Date ,
    @ColumnInfo(name = "jenisKelamin", defaultValue = "LAKI_LAKI") val jenisKelamin: JenisKelamin ,
)

enum class JenisKelamin {
    LAKI_LAKI,
    PEREMPUAN
}
