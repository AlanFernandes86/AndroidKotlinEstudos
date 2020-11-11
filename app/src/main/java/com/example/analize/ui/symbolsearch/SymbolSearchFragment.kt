package com.example.analize.ui.symbolsearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.analize.R
import com.example.analize.service.model.alphavantage.SymbolModel
import com.example.analize.ui.symbolsearch.adapter.SymbolSearchAdapter
import com.example.analize.ui.symbolsearch.listener.SymbolSearchListener
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class SymbolSearchFragment : Fragment() {

    private lateinit var mViewModel: SymbolSearchViewModel
    private val mAdapter = SymbolSearchAdapter()
    private lateinit var mListener: SymbolSearchListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mViewModel = ViewModelProvider(requireActivity()).get(SymbolSearchViewModel::class.java)

        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_symbol_search, container, false)

        val recycler = root.findViewById<RecyclerView>(R.id.recycler_fragment_symbol_search)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter

        mListener = object : SymbolSearchListener {
            override fun onClick(symbolModel: SymbolModel) {
                var message = "Success!"
                if(!mViewModel.selectedSymbol(symbolModel, requireActivity())) message = "Error!"
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }

        mAdapter.attachListener(mListener)

        observe()

        return root
    }

    private fun observe() {
        mViewModel.listSymbol.observe(viewLifecycleOwner, Observer {
            mAdapter.updateSymbolSearch(it)
        })
    }

}
