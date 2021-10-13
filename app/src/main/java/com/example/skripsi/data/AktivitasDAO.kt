package com.example.skripsi.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AktivitasDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAlarm(alarmModel: ModelAktivitas)

    @Update
    fun update(alarm: ModelAktivitas?)

    @Query("SELECT * FROM aktivitas_table ORDER BY id ASC")
    fun readAll(): LiveData<List<ModelAktivitas>>

    @Delete
    suspend fun delete(alarm: ModelAktivitas)

//    @Query("DELETE FROM aktivitas_table")
//    suspend fun deleteAll()
}