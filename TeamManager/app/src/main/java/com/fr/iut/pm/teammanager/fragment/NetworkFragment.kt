package com.fr.iut.pm.teammanager.fragment

import android.util.Log
import android.widget.ImageView
import com.fr.iut.pm.teammanager.api.ApiRequest
import com.fr.iut.pm.teammanager.api.OnDataLoaded
import com.fr.iut.pm.teammanager.model.User
import com.squareup.picasso.Picasso
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue


class NetworkFragment {

    companion object {
        private const val API_KEY = "RGAPI-500fecaa-5b15-41b2-b7a1-4e5d8d7f7d72"
        private const val URL = "https://euw1.api.riotgames.com"
    }

    fun setUserFromStringAndApi(name: String?, onDataLoaded: OnDataLoaded) {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(ApiRequest::class.java)
        val teamRequest = service.getUser(name, API_KEY)

        teamRequest.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()
                Log.d("test", "onResponse: $user")
                onDataLoaded.onSucess(user)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("API", "Error : $t")
                onDataLoaded.onFailure()
            }
        })
    }

    fun getUserApiFromString(name: String?, imgView: ImageView?) {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(ApiRequest::class.java)
        val teamRequest = service.getUser(name, API_KEY)

        teamRequest.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()
                Picasso.get()
                    .load("https://ddragon.leagueoflegends.com/cdn/11.3.1/img/profileicon/${user?.profileIconId}.png")
                    .into(imgView)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("API", "Error : $t")
            }
        })
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