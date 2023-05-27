package com.tubes.perkuliahan.k4.repository

import com.benasher44.uuid.uuid4
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import com.tubes.perkuliahan.k4.model.MataKuliah
import com.tubes.perkuliahan.k4.networks.MataKuliahApiService
import com.tubes.perkuliahan.k4.persistance.PerkuliahanDao
import javax.inject.Inject

class MataKuliahRepository @Inject constructor(
    private val api: MataKuliahApiService,
    private val dao: PerkuliahanDao
) : Repository {
    suspend fun loadItems (
        onSuccess: (List<MataKuliah>) -> Unit,
        onError: (List<MataKuliah>, String) -> Unit
    ) {
        val list: List<MataKuliah> = dao.getListMataKuliah()
        api.getAllMataKuliah()
            .suspendOnSuccess{
                data.whatIfNotNull {
                    it.data?.let {
                            list -> dao.insertAllMataKuliah(list)
                        val items: List<MataKuliah> = dao.getListMataKuliah()
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
        kode: String,
        nama: String,
        sks: Byte,
        praktikum: Boolean,
        deskripsi: String,
        onSuccess: (MataKuliah) -> Unit,
        onError: (MataKuliah?, String) -> Unit
    ) {
        val id = uuid4().toString()
        val item = MataKuliah(id, kode, nama, sks, praktikum, deskripsi)
        dao.insertAllMataKuliah(item)
        api.addMataKuliah(item)
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
        kode: String,
        nama: String,
        sks: Byte,
        praktikum: Boolean,
        deskripsi: String,
        onSuccess: (MataKuliah) -> Unit,
        onError: (MataKuliah?, String) -> Unit
    ) {
        val item = MataKuliah(id, kode, nama, sks, praktikum, deskripsi)
        dao.insertAllMataKuliah(item)
        api.updateMataKuliah(id, item)
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
        dao.deleteMataKuliah(id)
        api.deleteMataKuliah(id)
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
    suspend fun find(id: String): MataKuliah? {
        return  dao.findMataKuliah(id)
    }
}