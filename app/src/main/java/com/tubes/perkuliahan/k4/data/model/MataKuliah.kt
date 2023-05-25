package com.tubes.perkuliahan.k4.data.model

data class MataKuliah(
    val kode: String,
    val nama: String,
    val sks: Byte,
    val praktikum: Boolean,
    val deskripsi: String
)
