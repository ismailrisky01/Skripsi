package com.example.skripsi.ui.workout

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddd.androidutils.DoubleClick
import com.ddd.androidutils.DoubleClickListener
import com.example.skripsi.MainActivity
import com.example.skripsi.R
import com.example.skripsi.data.ViewModel
import com.example.skripsi.databinding.FragmentSplashScreenBinding
import com.example.skripsi.databinding.FragmentWorkoutBinding
import com.example.skripsi.ui.dashboard.DashboardFragment


class WorkoutFragment : Fragment() {
    private var _binding: FragmentWorkoutBinding? = null
    private val binding get() = _binding!!
    private val mViewModel by lazy {
        ViewModelProvider(this).get(ViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWorkoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = WorkoutAdapter()
        binding.apply {
            val kategori = arguments?.getString(DashboardFragment.TAG) as String
            binding.IDWorkoutTxtKeterangan.text =kategori
            mViewModel.readLatihanByKategori(kategori).observe(viewLifecycleOwner, Observer {
                IDWorkoutRecyclerview.layoutManager = LinearLayoutManager(requireContext())
                IDWorkoutRecyclerview.adapter = adapter
                adapter.setData(it)
            })

            val bundle = Bundle()
            bundle.putString(DashboardFragment.TAG, arguments?.getString(DashboardFragment.TAG))
            IDWorkoutBtnMulai.setOnClickListener {
                findNavController().navigate(
                    R.id.action_workoutFragment_to_doWorkoutFragment,
                    bundle
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}