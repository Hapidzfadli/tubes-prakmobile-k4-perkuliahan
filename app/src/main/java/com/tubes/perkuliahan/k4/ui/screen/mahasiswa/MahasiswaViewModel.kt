package com.tubes.perkuliahan.k4.ui.screen.mahasiswa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tubes.perkuliahan.k4.data.model.JenisKelamin
import com.tubes.perkuliahan.k4.data.model.Mahasiswa
import com.tubes.perkuliahan.k4.repository.MahasiswaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MahasiswaViewModel @Inject constructor(
    private val mahasiswaRepository: MahasiswaRepository
) : ViewModel() {
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _success: MutableLiveData<Boolean> = MutableLiveData(false)
    val success: LiveData<Boolean> get() = _success
    private val _toast: MutableLiveData<String> = MutableLiveData()
    val toast: LiveData<String> get() = _toast
    private val _list: MutableLiveData<List<Mahasiswa>> = MutableLiveData()
    val list: LiveData<List<Mahasiswa>> get() = _list

    suspend fun loadItems()
    {
        _isLoading.postValue(true)
        mahasiswaRepository.loadItems(onSuccess = {
            _isLoading.postValue(false)
            _list.postValue(it)
        }, onError = { list, message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
            _list.postValue(list)
        })
    }

    suspend fun insert(
        npm: String,
        nama: String,
        tanggalLahir: Date,
        jenisKelamin: JenisKelamin,
    )
    {
        _isLoading.postValue(true)
        mahasiswaRepository.insert(npm,nama,tanggalLahir, jenisKelamin,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            })
    }
    suspend fun loadItem(id: String, onSuccess: (Mahasiswa?) -> Unit)
    {
        val item = mahasiswaRepository.find(id)
        onSuccess(item)
    }

    suspend fun update(
        id: String,
        npm: String,
        nama: String,
        tanggalLahir: Date,
        jenisKelamin: JenisKelamin,
    )
    {
        _isLoading.postValue(true)
        mahasiswaRepository.update(id, npm, nama, tanggalLahir, jenisKelamin,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            })
    }
}