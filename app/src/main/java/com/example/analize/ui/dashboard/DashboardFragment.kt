package com.example.analize.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.analize.R
import com.example.analize.service.model.alphavantage.GlobalQuoteModel
import com.example.analize.service.model.alphavantage.SymbolModel
import com.example.analize.ui.dashboard.adapter.IndexAdapter
import com.example.analize.ui.dashboard.listener.IndexListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


class DashboardFragment : Fragment() {

    private lateinit var mViewModel: DashboardViewModel
    private lateinit var mListener: IndexListener
    private val mAdapter: IndexAdapter = IndexAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mViewModel = ViewModelProvider(requireActivity()).get(DashboardViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_dashboard_index, container, false)

        val recycler = root.findViewById<RecyclerView>(R.id.index_dashboard_recycler)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter

        mListener = object : IndexListener {
            override fun onClick(symbolModel: SymbolModel) {
                mViewModel.deleteSymbol(symbolModel)
            }
        }

        listener()
        observe()

        return root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.loadListSymbol()
        mViewModel.loadLocalGlobalQuotes()
    }

    private fun listener(){
        if (::mListener.isInitialized) mAdapter.attachListener(mListener)
    }

    private fun observe(){
        mViewModel.listSymbol.observe(viewLifecycleOwner, Observer {
            mAdapter.updateSymbol(it)
        })

        mViewModel.listGlobalQuote.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGlobalQuote(it)
        })
    }

}