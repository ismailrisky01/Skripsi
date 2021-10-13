package com.example.skripsi.data

import androidx.lifecycle.LiveData

class Repository(private val latihanDAO: LatihanDAO, private val aktivitasDAO: AktivitasDAO) {
    val readAllLatihan: LiveData<MutableList<ModelLatihan>> = latihanDAO.readAllLatihan()

    fun readLatihanByKategori(kategori: String): LiveData<MutableList<ModelLatihan>> {
        return latihanDAO.readAllLatihanByKategori(kategori)
    }
    suspend fun addLatihan(modelLatihan: ModelLatihan){
        latihanDAO.addLatihan(modelLatihan)
    }
    suspend fun deleteLatihan(modelLatihan: ModelLatihan){
        latihanDAO.delete(modelLatihan)
    }
}