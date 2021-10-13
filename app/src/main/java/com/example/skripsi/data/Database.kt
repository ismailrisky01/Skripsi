package com.example.skripsi.data

import android.content.Context
import android.os.Parcelable
import androidx.room.*
import androidx.room.Database
import kotlinx.parcelize.Parcelize
import java.util.*

@Database(entities = [ModelAktivitas::class, ModelLatihan::class], version = 1, exportSchema = false)
abstract class Database: RoomDatabase() {
    abstract fun aktivitasDAO(): AktivitasDAO
    abstract fun latihanDAO(): LatihanDAO

    companion object {
        @Volatile
        private var INSTANCE: com.example.skripsi.data.Database? = null

        fun getDatabase(context: Context): com.example.skripsi.data.Database {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    com.example.skripsi.data.Database::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

@Parcelize
@Entity(tableName = "aktivitas_table")
data class ModelAktivitas(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: String,
    val kegiatan:String,
    val lamaKegiatan:Int,
    val kaloriKegiatan:Int
):Parcelable

@Parcelize
@Entity(tableName = "latihan_table")
data class ModelLatihan(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val namaLatihan:String,
    val waktuLatihan:String,
    val kategoriLatihan:String
):Parcelable
