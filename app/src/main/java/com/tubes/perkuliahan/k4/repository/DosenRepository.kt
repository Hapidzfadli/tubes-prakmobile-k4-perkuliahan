package com.tubes.perkuliahan.k4.repository

import com.benasher44.uuid.uuid4
import com.skydoves.sandwich.*
import com.skydoves.whatif.whatIfNotNull
import com.tubes.perkuliahan.k4.data.model.Dosen
import com.tubes.perkuliahan.k4.data.model.Pendidikan
import com.tubes.perkuliahan.k4.networks.DosenApiService
import com.tubes.perkuliahan.k4.persistance.PerkuliahanDao
import javax.inject.Inject

class DosenRepository @Inject constructor(
    private val api:DosenApiService,
    private val dao:PerkuliahanDao
) : Repository {
    suspend fun loadItems (
        onSuccess: (List<Dosen>) -> Unit,
        onError: (List<Dosen>, String) -> Unit
    ) {
        val list: List<Dosen> = dao.getListDosen()
        api.all()
            .suspendOnSuccess{
                data.whatIfNotNull {
                    it.data?.let {
                        list -> dao.insertAllDosen(list)
                        val items: List<Dosen> = dao.getListDosen()
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
         nidn: String,
         nama: String,
         gelarDepan: String,
         gelarBelakang: String,
         pendidikan: Pendidikan,
         onSuccess: (Dosen) -> Unit,
         onError: (Dosen?, String) -> Unit
    ) {
        val id = uuid4().toString()
        val item = Dosen(id, nidn,nama,gelarDepan, gelarBelakang,pendidikan)
        dao.insertAllDosen(item)
        api.insert(item)
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
        nidn: String,
        nama: String,
        gelarDepan: String,
        gelarBelakang: String,
        pendidikan: Pendidikan,
        onSuccess: (Dosen) -> Unit,
        onError: (Dosen?, String) -> Unit
    ) {
        val item = Dosen(id, nidn,nama,gelarDepan, gelarBelakang,pendidikan)
        dao.insertAllDosen(item)
        api.update(id, item)
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
        dao.deleteDosen(id)
        api.delete(id)
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
    suspend fun find(id: String): Dosen? {
        return  dao.findDosen(id)
    }
}