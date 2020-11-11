package com.example.analize.ui.ibovespa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.analize.R

class IndexFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      return inflater.inflate(R.layout.fragment_ibovespa_index, container, false)

    }
}