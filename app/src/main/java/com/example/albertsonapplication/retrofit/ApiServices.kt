package com.example.albertsonapplication.retrofit

import com.sample.project.models.SearchDatum
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("dictionary.py")
    suspend fun getSearchData(@Query("sf")  searchKey:String): Response<List<SearchDatum>>
}