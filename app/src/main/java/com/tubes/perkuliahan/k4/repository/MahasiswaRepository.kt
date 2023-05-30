package com.tubes.perkuliahan.k4.repository

import com.benasher44.uuid.uuid4
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import com.tubes.perkuliahan.k4.model.JenisKelamin
import com.tubes.perkuliahan.k4.model.Mahasiswa
import com.tubes.perkuliahan.k4.networks.MahasiswaApiService
import com.tubes.perkuliahan.k4.persistance.PerkuliahanDao
import java.util.*
import javax.inject.Inject

class MahasiswaRepository @Inject constructor(
    private val api: MahasiswaApiService,
    private val dao: PerkuliahanDao
) : Repository {
    suspend fun loadItems (
        onSuccess: (List<Mahasiswa>) -> Unit,
        onError: (List<Mahasiswa>, String) -> Unit
    ) {
        val list: List<Mahasiswa> = dao.getListMahasiswa()
        api.getAllMahasiswa()
            .suspendOnSuccess{
                data.whatIfNotNull {
                    it.data?.let {
                            list -> dao.insertAllMahasiswa(list)
                        val items: List<Mahasiswa> = dao.getListMahasiswa()
                        onSuccess(items)
                    }
                }
            }
            .suspendOnError {
                onError(list, message())
            }
            .suspendOnException {
                onError(list, message())
            }
    }

    suspend fun insert (
        npm: String,
        nama: String,
        tanggal_lahir: Date,
        jenis_kelamin: JenisKelamin,
        onSuccess: (Mahasiswa) -> Unit,
        onError: (Mahasiswa?, String) -> Unit
    ) {
        val id = uuid4().toString()
        val item = Mahasiswa(id, npm, nama, tanggal_lahir, jenis_kelamin)
        dao.insertAllMahasiswa(item)
        api.addMahasiswa(item)
            .suspendOnSuccess {
                onSuccess(item)
            }
            .suspendOnError {
                onError(item, message())
            }
            .suspendOnException {
                onError(item, message())
            }
    }

    suspend fun update (
        id: String,
        npm: String,
        nama: String,
        tanggal_lahir: Date,
        jenis_kelamin: JenisKelamin,
        onSuccess: (Mahasiswa) -> Unit,
        onError: (Mahasiswa?, String) -> Unit
    ) {
        val item = Mahasiswa(id, npm, nama, tanggal_lahir, jenis_kelamin)
        dao.insertAllMahasiswa(item)
        api.updateMahasiswa(id, item)
            .suspendOnSuccess {
                onSuccess(item)
            }
            .suspendOnError {
                onError(item, message())
            }
            .suspendOnException {
                onError(item, message())
            }
    }
    suspend fun delete(id: String, onSuccess: () -> Unit,
                       onError: (String) -> Unit) {
        dao.deleteMahasiswa(id)
        api.deleteMahasiswa(id)
            .suspendOnSuccess {
                data.whatIfNotNull {
                    onSuccess()
                }
            }
            .suspendOnError {
                onError(message())
            }
            .suspendOnException {
                onError(message())
            }
    }
    suspend fun find(id: String): Mahasiswa? {
        return  dao.findMahasiswa(id)
    }
}