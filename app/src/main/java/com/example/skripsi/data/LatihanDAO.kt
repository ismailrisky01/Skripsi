package com.example.skripsi.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface LatihanDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLatihan(modelLatihan: ModelLatihan)

    @Update
    fun update(modelLatihan: ModelLatihan?)

    @Query("SELECT * FROM latihan_table ORDER BY id ASC")
    fun readAllLatihan(): LiveData<MutableList<ModelLatihan>>


    @Query("SELECT * FROM latihan_table WHERE kategoriLatihan LIKE :kategori ORDER BY id ASC")
    fun readAllLatihanByKategori(kategori:String): LiveData<MutableList<ModelLatihan>>

    @Delete
    suspend fun delete(modelLatihan: ModelLatihan)

    @Query("DELETE FROM latihan_table")
    suspend fun deleteAllLatihan()
}