package com.pberrueco.apipractica.ui.recycler

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pberrueco.apipractica.data.data_store.DataStoreManager
import com.pberrueco.apipractica.data.network.PracticApi
import com.pberrueco.apipractica.data.network.model.PracticResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private var _userName: MutableLiveData<String?> = MutableLiveData(null)
    val userName: LiveData<String?> = _userName

    private val _homeWorkResponse = MutableLiveData<List<PracticResponse>>()
    val homeWorkResponse: LiveData<List<PracticResponse>> = _homeWorkResponse

    fun getUserName(context: Context) {
        viewModelScope.launch(Dispatchers.IO){
            DataStoreManager.getData(context).collect{ userName ->
                if(userName != "No Data"){
                    _userName.postValue(userName)
                }
            }
        }

    }

    fun getHomeWork(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = PracticApi.service.getHomeWork(userName, "u-tad")
                if (response.isSuccessful) {
                    val homeWorkList = response.body()
                    _homeWorkResponse.postValue(homeWorkList!!)
                } else {
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}