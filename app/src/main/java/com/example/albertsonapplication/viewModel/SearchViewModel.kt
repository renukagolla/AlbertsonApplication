package com.example.albertsonapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.albertsonapplication.model.SearchModel
import com.example.albertsonapplication.repository.MainActivityRepository

class SearchViewModel: ViewModel() {
    var servicesLiveData: MutableLiveData<List<SearchModel>>? = null

    fun getServiceData(filterKey:String) : LiveData<List<SearchModel>>? {
//        servicesLiveData = MainActivityRepository.getServicesApiCall("ADT")
        servicesLiveData = MainActivityRepository.getServicesApiCall(filterKey)
        return servicesLiveData
    }


}