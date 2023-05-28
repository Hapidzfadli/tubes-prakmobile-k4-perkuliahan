package com.tubes.perkuliahan.k4.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class MataKuliah(
    @PrimaryKey val id: String,
    val kode: String,
    val nama: String,
    @ColumnInfo(name = "sks", defaultValue = "2") val sks: Byte,
    @ColumnInfo(name = "praktikum", defaultValue = "0") val praktikum: Int,
    @ColumnInfo(name = "deskripsi", defaultValue = "matkul") val deskripsi: String,
)
