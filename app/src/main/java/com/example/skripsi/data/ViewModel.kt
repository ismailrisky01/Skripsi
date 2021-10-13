package com.example.skripsi.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {
    val readAllLatihan: LiveData<MutableList<ModelLatihan>>
    private val repository: Repository

    init {
        val latihanDAO = Database.getDatabase(application).latihanDAO()
        val aktivitasDAO = Database.getDatabase(application).aktivitasDAO()
        repository = Repository(latihanDAO, aktivitasDAO)
        readAllLatihan = repository.readAllLatihan
    }

    fun addlatihan(modelLatihan: ModelLatihan) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addLatihan(modelLatihan)
        }
    }

    fun readLatihanByKategori(kategori: String): LiveData<MutableList<ModelLatihan>> {
        return repository.readLatihanByKategori(kategori)
    }

    fun deleteLatihan(modelLatihan: ModelLatihan) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteLatihan(modelLatihan)
        }
    }
}