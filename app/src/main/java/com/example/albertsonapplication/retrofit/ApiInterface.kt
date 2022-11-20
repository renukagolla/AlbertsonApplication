package com.example.albertsonapplication.retrofit

import com.example.albertsonapplication.model.SearchModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("acromine/dictionary.py")
    fun getAllData(@Query("sf") sf: String?,
                @Query("lf") lf: String?): Call<List<SearchModel>>

}