package com.example.skripsi.ui.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.skripsi.R
import com.example.skripsi.data.ModelLatihan
import com.example.skripsi.databinding.ItemLatihanBinding

class WorkoutAdapter() : RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {
    companion object{
        val idLatihan = "KeyID"
    }
    private var dataAdapter = mutableListOf<ModelLatihan>()
    fun setData(data: MutableList<ModelLatihan>) {
        dataAdapter = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLatihanBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataAdapter[position]
        holder.initData(data)
    }

    override fun getItemCount(): Int {
        return dataAdapter.size
    }

    class ViewHolder(val binding: ItemLatihanBinding) : RecyclerView.ViewHolder(binding.root) {
        fun initData(modelLatihan: ModelLatihan) {
            binding.apply {
                IDItemLatihanTxtNamaGerakan.text = modelLatihan.namaLatihan
                IDItemLatihanTxtKetGerakan.text = modelLatihan.waktuLatihan
            }

        }
    }
}