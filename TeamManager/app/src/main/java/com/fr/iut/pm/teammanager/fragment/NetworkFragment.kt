package com.fr.iut.pm.teammanager.fragment

import com.fr.iut.pm.teammanager.api.ApiRequest
import com.fr.iut.pm.teammanager.model.User
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class NetworkFragment {

    companion object {
        private const val API_KEY = "RGAPI-de8c1f1e-42a7-4148-a9ae-3228ca289fc6"
        private const val URL = "https://euw1.api.riotgames.com"
    }

    /*fun getUserFromStringAndApi(name: String?, onDataLoaded: OnDataLoaded) {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(ApiRequest::class.java)
        val teamRequest = service.getUser(name, API_KEY)

        teamRequest.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()
                onDataLoaded.onSuccess(user)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("API", "Error : $t")
                onDataLoaded.onFailure()
            }
        })
    }*/

    fun getUserFromStringAndApi(name: String?): User {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(ApiRequest::class.java)
        val teamRequest = service.getUser(name, API_KEY)

        return teamRequest.execute().body() ?: User("", 0, "", "")
    }

    /*fun getUserHistory(accountId: String?) {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(ApiRequest::class.java)
        val teamRequest = service.getHistory(accountId, API_KEY)

        teamRequest.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("API", "Error : $t")
            }
        })
    }*/
}