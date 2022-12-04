package com.example.albertsonapplication.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.project.models.LF
import com.sample.project.network.RetrofitBuilder
import kotlinx.coroutines.*

class SearchViewModel: ViewModel() {
    val apiService = RetrofitBuilder.apiService

    var job: Job? = null
    val loading = MutableLiveData<Boolean>()
    val usersLoadError = MutableLiveData<String?>()

    val  searchResults = MutableLiveData<List<LF>>()

    val  etInput = MutableLiveData<String>()


    fun fetchSearchData(input:String) {

        loading.value = true
        job = CoroutineScope(Dispatchers.IO).launch {

            val response = apiService.getSearchData(searchKey = input)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful ) {
                    loading.value = false
                    if(response.body()?.isNotEmpty() == true){
                        searchResults.postValue(response.body()?.get(0)?.lfs)
                    }else{
                        searchResults.postValue(mutableListOf())
                    }


                } else {
                    onError("Error : ${response.message()} ")
                }

                Log.v("DATA_DATA",response.toString())

            }
        }

        //usersLoadError.value = ""
        //loading.value = false
    }

    private fun onError(message: String) {
        usersLoadError.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}