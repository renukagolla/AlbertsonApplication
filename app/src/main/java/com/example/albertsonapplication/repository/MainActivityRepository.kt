package com.example.albertsonapplication.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.albertsonapplication.model.SearchModel
import com.example.albertsonapplication.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainActivityRepository {

    val acronymList = MutableLiveData<List<SearchModel>>()


    fun getServicesApiCall(key: String): MutableLiveData<List<SearchModel>> {

        val call = RetrofitClient.apiInterface.getAllData(key,"")

        call.enqueue(object : Callback<List<SearchModel>> {
            override fun onResponse(
                call: Call<List<SearchModel>>,
                response: Response<List<SearchModel>>
            ) {
                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()
                Log.v("Data", data.toString())
                acronymList.value = data
            }

            override fun onFailure(call: Call<List<SearchModel>>, t: Throwable) {
                Log.v("FailureDEBUG : ", t.message.toString())

            }
        })

        return acronymList
    }
}





