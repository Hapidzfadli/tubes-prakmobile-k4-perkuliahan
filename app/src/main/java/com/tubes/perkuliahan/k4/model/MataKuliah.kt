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
    val sks: Byte,
    val praktikum: Int,
    val deskripsi: String,
)

enum class Praktikum(var value: Int) {
    PRAKTIKUM(1),
    BUKAN_PRAKTIKUM(0)
}