package com.prakhar.quotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Setting up ViewModel
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModel::class.java)
        setQuote(mainViewModel.getQuote())

    }

    private fun setQuote(quote: Quote){
        quoteText.text = quote.text
        quoteAuthor.text = quote.author
    }

    fun onPrevious(view: View) {
        setQuote(mainViewModel.previousQuote())
    }

    fun onNext(view: View) {
        setQuote(mainViewModel.nextQuote())
    }

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text)
        startActivity(intent)
    }
}