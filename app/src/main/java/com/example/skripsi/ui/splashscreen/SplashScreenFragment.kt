package com.example.skripsi.ui.splashscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.skripsi.R
import com.example.skripsi.data.ModelLatihan
import com.example.skripsi.data.ViewModel
import com.example.skripsi.databinding.FragmentSplashScreenBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashScreenFragment : Fragment() {
    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!
    private val mViewModel by lazy {
        ViewModelProvider(this).get(ViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun initData(){
        mViewModel.readAllLatihan.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()){
                val data = ArrayList<ModelLatihan>()
                data.add(ModelLatihan(0,"Bodyweight squats","10 X","Beginner"))
                data.add(ModelLatihan(0,"Push-ups","10 X","Beginner"))
                data.add(ModelLatihan(0,"Walking lunges","5 X","Beginner"))
                data.add(ModelLatihan(0,"Planks","20 Sec","Beginner"))
                data.add(ModelLatihan(0,"Jumping jacks","15 X","Beginner"))
                data.add(ModelLatihan(0,"Sit-ups","15 X","Beginner"))

                data.add(ModelLatihan(0,"Bodyweight squats","20 X","Advanced"))
                data.add(ModelLatihan(0,"Push-ups","20 X","Advanced"))
                data.add(ModelLatihan(0,"Walking lunges","10 X","Advanced"))
                data.add(ModelLatihan(0,"Planks","40 Sec","Advanced"))
                data.add(ModelLatihan(0,"Jumping jacks","20 X","Advanced"))
                data.add(ModelLatihan(0,"Sit-ups","20 X","Advanced"))
                data.forEach {
                    mViewModel.addlatihan(it)
                }
                GlobalScope.launch {
                    delay(3000)
                    requireActivity().runOnUiThread {
                        findNavController().navigate(R.id.action_splashScreenFragment_to_dashboardFragment)
                    }
                }
            }else{
                GlobalScope.launch {
                    delay(3000)
                    requireActivity().runOnUiThread {
                        findNavController().navigateUp()
                        findNavController().navigate(R.id.action_splashScreenFragment_to_dashboardFragment)
                    }
                }
            }
        })
    }

}