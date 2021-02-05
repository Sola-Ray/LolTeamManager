package com.fr.iut.pm.teammanager.fragment

import android.util.Log
import com.fr.iut.pm.teammanager.api.ApiRequest
import com.fr.iut.pm.teammanager.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkFragment {

    private val url = "https://euw1.api.riotgames.com/"

    fun testApi() {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(ApiRequest::class.java)
        val teamRequest = service.getUser("RGAPI-c53f4941-a1c5-4b09-9580-44702dcdb545")

        teamRequest.enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()
                Log.d("TEST","User ${user?.username} ${user?.id}")
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("TEST", "Error : $t")
            }
        })
    }
}