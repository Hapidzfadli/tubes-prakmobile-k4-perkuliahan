package com.tubes.perkuliahan.k4.persistance

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tubes.perkuliahan.k4.data.model.Dosen
import com.tubes.perkuliahan.k4.model.Mahasiswa
import com.tubes.perkuliahan.k4.model.MataKuliah

@Dao
interface PerkuliahanDao {
    // DAO untuk entitas Dosen
    @Query("SELECT * FROM Dosen ORDER BY nama")
    fun loadAllDosen(): LiveData<List<Dosen>>

    @Query("SELECT * FROM Dosen ORDER BY nama")
    suspend fun getListDosen(): List<Dosen>

    @Query("SELECT * FROM Dosen WHERE id = :id")
    suspend fun findDosen(id: String): Dosen?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDosen(vararg items: Dosen)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDosen(items: List<Dosen>)

    @Delete
    fun deleteDosen(item: Dosen)

    @Query("DELETE FROM Dosen WHERE id = :id")
    fun deleteDosen(id: String)

    // DAO untuk entitas Mahasiswa
    @Query("SELECT * FROM Mahasiswa ORDER BY nama")
    fun loadAllMahasiswa(): LiveData<List<Mahasiswa>>

    @Query("SELECT * FROM Mahasiswa ORDER BY nama")
    suspend fun getListMahasiswa(): List<Mahasiswa>

    @Query("SELECT * FROM Mahasiswa WHERE id = :id")
    suspend fun findMahasiswa(id: String): Mahasiswa?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMahasiswa(vararg items: Mahasiswa)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMahasiswa(items: List<Mahasiswa>)

    @Delete
    fun deleteMahasiswa(item: Mahasiswa)

    @Query("DELETE FROM Mahasiswa WHERE id = :id")
    fun deleteMahasiswa(id: String)

    // DAO untuk entitas MataKuliah
    @Query("SELECT * FROM MataKuliah ORDER BY nama")
    fun loadAllMataKuliah(): LiveData<List<MataKuliah>>

    @Query("SELECT * FROM MataKuliah ORDER BY nama")
    suspend fun getListMataKuliah(): List<MataKuliah>

    @Query("SELECT * FROM MataKuliah WHERE id = :id")
    suspend fun findMataKuliah(id: String): MataKuliah?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMataKuliah(vararg items: MataKuliah)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMataKuliah(items: List<MataKuliah>)

    @Delete
    fun deleteMataKuliah(item: MataKuliah)

    @Query("DELETE FROM MataKuliah WHERE id = :id")
    fun deleteMataKuliah(id: String)
}