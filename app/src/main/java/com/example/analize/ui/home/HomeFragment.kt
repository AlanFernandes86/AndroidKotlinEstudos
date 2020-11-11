package com.example.analize.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.analize.R
import com.example.analize.ui.bottomnavigation.BottomNavigationViewModel


class HomeFragment : Fragment() {

    private lateinit var mViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home_index, container, false)

        observe()

        return root
    }

    private fun observe(){

    }
}