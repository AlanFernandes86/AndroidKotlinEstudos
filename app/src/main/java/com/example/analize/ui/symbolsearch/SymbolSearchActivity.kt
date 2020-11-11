package com.example.analize.ui.symbolsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.analize.R
import com.example.analize.service.constants.ConstantsAlphaVantage
import kotlinx.android.synthetic.main.activity_symbol_search.*
import kotlinx.android.synthetic.main.fragment_symbol_search.*

open class SymbolSearchActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: SymbolSearchViewModel
    private lateinit var mEditText: EditText
    private lateinit var mButton: Button
    private lateinit var mText: TextView
    private lateinit var mList: RecyclerView
    private var hasApiKey = true
    private var hasList = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symbol_search)

        load()
        setListeners()
        observe()
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == mButton.id) {
            buttonSearch()
        }
    }

    private fun load() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mViewModel = ViewModelProvider(this).get(SymbolSearchViewModel::class.java)
        mEditText = findViewById(edit_symbol_search.id)
        mButton = findViewById(button_search.id)
        mText = findViewById(text_fragment_symbol_search.id)
        mList = findViewById(recycler_fragment_symbol_search.id)
        mList.visibility = View.GONE
    }

    private fun setListeners() {
        mButton.setOnClickListener(this)
    }

    private fun observe() {
        mViewModel.messageApiKey.observe(this, Observer {
            when (it) {
                ConstantsAlphaVantage.SYMBOL_SEARCH_MESSAGE.START -> {
                    messageStart()
                }
                ConstantsAlphaVantage.SYMBOL_SEARCH_MESSAGE.NOK_APIKEY -> {
                    messageNokApiKey()
                }
                ConstantsAlphaVantage.SYMBOL_SEARCH_MESSAGE.OK_APIKEY -> {
                    messageOkApiKey()
                }
                ConstantsAlphaVantage.SYMBOL_SEARCH_MESSAGE.INVALID_API -> {
                    messageInvalidAPI()
                }
                ConstantsAlphaVantage.SYMBOL_SEARCH_MESSAGE.NOK_COMPANY -> {
                    messageNokCompany()
                }
                ConstantsAlphaVantage.SYMBOL_SEARCH_MESSAGE.OK_COMPANY -> {
                    messageOkCompany()
                }
                else -> {
                    TODO("Not yet implemented")
                }
            }
        })


    }

    private fun messageStart() {
        mEditText.hint = getString(R.string.hint_edit_symbol_search)
        mEditText.setText("")
        mButton.text = getString(R.string.button_search)
        hasApiKey = true
        mText.text = getString(R.string.message_digite_empresa)
    }

    private fun messageNokApiKey() {
        mEditText.hint = getString(R.string.hint_button_save_APIKEY)
        mEditText.setText("")
        mButton.text = getString(R.string.button_save_API)
        hasApiKey = false
        mText.text = getString(R.string.message_digite_apikey)
    }

    private fun messageOkApiKey() {
        mEditText.hint = getString(R.string.hint_edit_symbol_search)
        mEditText.setText("")
        mButton.text = getString(R.string.button_search)
        hasApiKey = true
        mText.text = "Chave de Acesso OK! Digite o nome da empresa para começar."
    }

    private fun messageNokCompany() {
        mText.visibility = View.VISIBLE
        mList.visibility = View.GONE
        mEditText.setText("")
        mText.text = "Empresa não encontrada."
    }

    private fun messageInvalidAPI() {
        TODO("Not yet implemented")
    }

    private fun messageOkCompany() {
        mText.visibility = View.GONE
        mList.visibility = View.VISIBLE
    }

    private fun buttonSearch() {
        val text = edit_symbol_search.text.toString()
        if (hasApiKey) {
            mViewModel.loadRecyclerFragment(text)
        } else {
            mViewModel.apiKey(text)
        }
    }


}