package com.example.albertsonapplication.view

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.albertsonapplication.databinding.ActivityMainBinding
import com.example.albertsonapplication.viewModel.SearchViewModel
import com.sample.project.models.LF


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: SearchViewModel

    lateinit var adapter: SearchDataAdapter

    private var searchResults: MutableList<LF> = mutableListOf<LF>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        adapter = SearchDataAdapter(this, searchResults)

        binding.adapter = adapter
        if(isNetworkAvailable()) {
            observeViewModel()
        }
        else{
            Toast.makeText(this,"Network Not Available",Toast.LENGTH_LONG).show()
        }



    }

    private fun observeViewModel() {

        viewModel.etInput.observe(this, Observer { s -> viewModel.fetchSearchData(s) })

        viewModel.searchResults.observe(
            this,
            Observer { results -> updateData(results) })


        /* viewModel.usersLoadError.observe(this, Observer { isError ->
             binding.listError.visibility = if (isError == "") View.GONE else View.VISIBLE
         })*/

        viewModel.loading.observe(this, Observer { isLoading ->
            if (isLoading) {
                binding.loadingView.visibility = View.VISIBLE
            } else {
                binding.loadingView.visibility = View.INVISIBLE
            }

        })

    }

    private fun updateData(results: List<LF>?) {

        binding.dataEmpty = results.isNullOrEmpty()

        searchResults.clear()

        adapter.updateItems(results)
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager?.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
