package com.example.albertsonapplication.view

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.albertsonapplication.R
import com.example.albertsonapplication.databinding.ActivityMainBinding
import com.example.albertsonapplication.model.MainLfS
import com.example.albertsonapplication.viewModel.SearchViewModel


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var searchViewModel: SearchViewModel
    lateinit var searchAdapter: SearchAdapter
    var searchData = mutableListOf<MainLfS>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        binding.viewModel = searchViewModel
        binding.lifecycleOwner = this
        setSupportActionBar(findViewById(R.id.my_toolbar))
        binding.searchRecycler.setHasFixedSize(true)
        searchData.clear();
        binding.setNoData(true);
        //init the Custom adataper
        searchAdapter = SearchAdapter(searchData as ArrayList<MainLfS>, this);
        binding.adapter = searchAdapter
        if(isNetworkAvailable()) {
            getData("")
            searchListener()
        }
        else{
            Toast.makeText(this,"Network Not Available",Toast.LENGTH_LONG).show()
        }

    }
    private fun getData(s: String) {
        if (s.isEmpty()) {
            searchData.clear()
            searchAdapter.updateData(searchData)
            searchAdapter.notifyDataSetChanged()
            return
        }

        searchViewModel.getServiceData(s)!!.observe(this, Observer { acronymData ->
            val data = acronymData
            Log.v("ServiceDtaa", "" + data)
            if (data.isEmpty())
                binding.setNoData(true);
            else
                binding.setNoData(false);

            if (data.isNotEmpty()) {
                searchData.addAll(data.get(0).lfs)
                searchAdapter.updateData(data.get(0).lfs);
                searchAdapter.notifyDataSetChanged()
            }
            else{
                searchData.clear()
                searchAdapter.updateData(searchData)
                searchAdapter.notifyDataSetChanged()
            }

        })
    }

    fun searchListener() {
        binding.searchKey.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                getData(p0.toString())

            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager?.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}