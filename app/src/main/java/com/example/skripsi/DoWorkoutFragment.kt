package com.example.skripsi

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.skripsi.data.ViewModel
import com.example.skripsi.databinding.FragmentDashboardBinding
import com.example.skripsi.databinding.FragmentDoWorkoutBinding
import com.example.skripsi.ui.dashboard.DashboardFragment


class DoWorkoutFragment : Fragment() {

    private var _binding: FragmentDoWorkoutBinding? = null
    private val binding get() = _binding!!
    private val mViewModel by lazy {
        ViewModelProvider(this).get(ViewModel::class.java)
    }
    private var progressStatus = 0
    private val handler: Handler = Handler()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDoWorkoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startThread()
    }

    fun startThread() {
        Thread {
            while (progressStatus < 3) {
                progressStatus += 1
                // Update the progress bar and display the
                //current value in the text view
                Handler(Looper.getMainLooper()).post(Runnable {
                    binding.progressBar.progress = progressStatus
                    binding.IDDoWorkTxtStatus.text = progressStatus.toString()
                    if(progressStatus==3){
                        val bundle =Bundle()
                        bundle.putString(
                            DashboardFragment.TAG,arguments?.getString(
                                DashboardFragment.TAG))
                        findNavController().navigate(R.id.action_doWorkoutFragment_to_startWorkOutFragment,bundle)
                    }
                })
                try {
                    // Sleep for 200 milliseconds.
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}