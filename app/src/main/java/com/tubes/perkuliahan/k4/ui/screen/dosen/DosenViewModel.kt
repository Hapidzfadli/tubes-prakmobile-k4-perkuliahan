package com.tubes.perkuliahan.k4.ui.screen.dosen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tubes.perkuliahan.k4.data.model.Dosen
import com.tubes.perkuliahan.k4.data.model.Pendidikan
import com.tubes.perkuliahan.k4.repository.DosenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DosenViewModel @Inject constructor(
    private val dosenRepository: DosenRepository
) : ViewModel() {
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _success: MutableLiveData<Boolean> = MutableLiveData(false)
    val success: LiveData<Boolean> get() = _success
    private val _toast: MutableLiveData<String> = MutableLiveData()
    val toast: LiveData<String> get() = _toast
    private val _list: MutableLiveData<List<Dosen>> = MutableLiveData()
    val list: LiveData<List<Dosen>> get() = _list

    suspend fun loadItems()
    {
        _isLoading.postValue(true)
        dosenRepository.loadItems(onSuccess = {
            _isLoading.postValue(false)
            _list.postValue(it)
        }, onError = { list, message ->
            _toast.postValue(message)
            _isLoading.postValue(false)
            _list.postValue(list)
        })
    }

    suspend fun insert(
        nidn: String,
        nama: String,
        gelarDepan: String,
        gelarBelakang: String,
        pendidikan: Pendidikan,
    )
    {
        _isLoading.postValue(true)
        dosenRepository.insert(nidn,nama,gelarDepan, gelarBelakang,pendidikan,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            })
    }
    suspend fun loadItem(id: String, onSuccess: (Dosen?) -> Unit)
    {
        val item = dosenRepository.find(id)
        onSuccess(item)
    }

    suspend fun update(
        id: String,
        nidn: String,
        nama: String,
        gelarDepan: String,
        gelarBelakang: String,
        pendidikan: Pendidikan,
    )
    {
        _isLoading.postValue(true)
        dosenRepository.update(id, nidn, nama, gelarDepan, gelarBelakang, pendidikan,
            onError = { item, message ->
                _toast.postValue(message)
                _isLoading.postValue(false)
            }, onSuccess = {
                _isLoading.postValue(false)
                _success.postValue(true)
            })
    }
}