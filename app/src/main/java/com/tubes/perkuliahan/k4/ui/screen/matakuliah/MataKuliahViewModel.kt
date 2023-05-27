package com.tubes.perkuliahan.k4.ui.screen.matakuliah

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tubes.perkuliahan.k4.model.MataKuliah
import com.tubes.perkuliahan.k4.repository.MataKuliahRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MataKuliahViewModel @Inject constructor(
    private val mataKuliahRepository: MataKuliahRepository
) : ViewModel() {
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _success: MutableLiveData<Boolean> = MutableLiveData(false)
    val success: LiveData<Boolean> get() = _success
    private val _toast: MutableLiveData<String> = MutableLiveData()
    val toast: LiveData<String> get() = _toast
    private val _list: MutableLiveData<List<MataKuliah>> = MutableLiveData()
    val list: LiveData<List<MataKuliah>> get() = _list

    suspend fun loadItems()
    {
        _isLoading.postValue(true)
        mataKuliahRepository.loadItems(onSuccess = {
            _isLoading.postValue(false)
            _list.postValue(it)
        }, onError = { list, message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
            _list.postValue(list)
        })
    }

    suspend fun insert(
        kode: String,
        nama: String,
        sks: Byte,
        praktikum: Boolean,
        deskripsi: String,
    )
    {
        _isLoading.postValue(true)
        mataKuliahRepository.insert(kode, nama, sks, praktikum, deskripsi,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            })
    }
    suspend fun loadItem(id: String, onSuccess: (MataKuliah?) -> Unit)
    {
        val item = mataKuliahRepository.find(id)
        onSuccess(item)
    }

    suspend fun update(
        id: String,
        kode: String,
        nama: String,
        sks: Byte,
        praktikum: Boolean,
        deskripsi: String,
    )
    {
        _isLoading.postValue(true)
        mataKuliahRepository.update(id, kode, nama, sks, praktikum, deskripsi,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            })
    }
}