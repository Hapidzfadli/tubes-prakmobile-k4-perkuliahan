package com.tubes.perkuliahan.k4.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MataKuliah(
    @PrimaryKey val id: String,
    val kode: String,
    val nama: String,
    val sks: Byte,
    val praktikum: Boolean,
    val deskripsi: String
)
