package com.example.skripsi.ui.dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.skripsi.R
import com.example.skripsi.data.ViewModel
import com.example.skripsi.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val mViewModel by lazy {
        ViewModelProvider(this).get(ViewModel::class.java)
    }

    companion object {
        val TAG = "Skripsi"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initUi()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun initUi() {
        binding.apply {
            IDDashboardBeginner.setOnClickListener {
                val bundle=Bundle()
                bundle.putString(TAG,"Beginner")
                findNavController().navigate(R.id.action_dashboardFragment_to_workoutFragment,bundle)

            }
            IDDashboardAdvanced.setOnClickListener {
                val bundle=Bundle()
                bundle.putString(TAG,"Advanced")
                findNavController().navigate(R.id.action_dashboardFragment_to_workoutFragment,bundle)
            }
        }
    }

    fun initData() {
        mViewModel.readAllLatihan.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                Log.d(TAG, "Data Kosong")
            } else {
                Log.d(TAG, "Ada Data")
            }
        })

    }

}