package com.raja.rv_with_databinding.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raja.rv_with_databinding.data.model.Item
import com.raja.rv_with_databinding.data.model.User
import com.raja.rv_with_databinding.data.network.RetroInstance
import com.raja.rv_with_databinding.data.network.RetroService
import com.raja.rv_with_databinding.ui.adapter.AdapterUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {

    var userList: MutableLiveData<User> = MutableLiveData()
    var adapter : AdapterUser = AdapterUser()


    fun setAdapterData(data: ArrayList<Item>) {
        adapter.setDataList(data)
    }

    fun makeAPICall(input: String) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getDataFromAPI(input)
        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                userList.postValue(null)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    userList.postValue(response.body())
                } else {
                    userList.postValue(null)
                }
            }
        })
    }

}