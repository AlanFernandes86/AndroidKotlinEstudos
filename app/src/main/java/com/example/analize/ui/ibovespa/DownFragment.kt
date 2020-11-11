package com.example.analize.ui.ibovespa

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.analize.R
import com.example.analize.ui.ibovespa.adapter.IndexAdapter


class DownFragment : Fragment() {

    private val mAdapter = IndexAdapter()
    private lateinit var mViewModel: IbovespaViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mViewModel = ViewModelProvider(requireActivity()).get(IbovespaViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_ibovespa_recycler, container, false)

        val recycler = root.findViewById<RecyclerView>(R.id.index_ibovespa_recycler)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter
        recycler.isNestedScrollingEnabled = false

        observe()

        return root
    }

    private fun observe(){
        mViewModel.listUpDown.observe(viewLifecycleOwner, Observer {
            mAdapter.update(it.downStocks)
        })
    }

}