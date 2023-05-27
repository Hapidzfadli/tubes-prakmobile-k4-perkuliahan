package com.tubes.perkuliahan.k4.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tubes.perkuliahan.k4.data.model.Dosen
import com.tubes.perkuliahan.k4.data.model.Mahasiswa
import com.tubes.perkuliahan.k4.model.MataKuliah

@Database(entities = [Dosen::class, Mahasiswa::class, MataKuliah::class], version = 3)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract  fun perkuliahanDao(): PerkuliahanDao
}