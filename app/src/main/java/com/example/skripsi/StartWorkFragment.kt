package com.example.skripsi

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.skripsi.data.ModelLatihan
import com.example.skripsi.data.ViewModel
import com.example.skripsi.databinding.FragmentDashboardBinding
import com.example.skripsi.databinding.FragmentStartWorkOutBinding
import com.example.skripsi.ui.dashboard.DashboardFragment


class StartWorkFragment : Fragment() {
    private var _binding: FragmentStartWorkOutBinding? = null
    private val binding get() = _binding!!
    private val mViewModel by lazy {
        ViewModelProvider(this).get(ViewModel::class.java)
    }
    private var progressStatus = 0
    private var data = ArrayList<ModelLatihan>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStartWorkOutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val kategori = arguments?.getString(DashboardFragment.TAG)
        mViewModel.readLatihanByKategori(kategori.toString()).observe(viewLifecycleOwner, Observer {
            initDisplay(it[progressStatus])
            it.forEach {
            data.add(it)
            }
        })
        initAction()
    }
    fun initAction(){
        binding.IDStartWorkBtnNext.setOnClickListener {
            if (progressStatus<data.size-1){
                progressStatus++
                initDisplay(data[progressStatus])
                if (progressStatus==data.size-1){
                    binding.IDStartWorkBtnNext.text = "Selesai"
                }
            }
        }
    }
    fun initDisplay(modelLatihan: ModelLatihan){
        binding.apply {
            IDDoWorkTxtKegiatan.text = modelLatihan.namaLatihan
            IDStartWorkTxtTime.text = modelLatihan.waktuLatihan

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}