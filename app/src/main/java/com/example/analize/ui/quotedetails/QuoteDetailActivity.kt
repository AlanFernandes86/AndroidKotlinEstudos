package com.example.analize.ui.quotedetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.analize.R

class QuoteDetailActivity : AppCompatActivity() {

    lateinit var mViewModel: QuoteDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_detail)

        mViewModel = ViewModelProvider(this).get(QuoteDetailViewModel::class.java)

        observe()


    }

    private fun observe(){


    }
}