package com.fr.iut.pm.teammanager.fragment

import android.util.Log
import android.widget.ImageView
import com.fr.iut.pm.teammanager.api.ApiRequest
import com.fr.iut.pm.teammanager.model.User
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkFragment {

    companion object {
        private const val API_KEY = "RGAPI-fe5874ac-8519-4c46-b55f-6b7901008966"
        private const val URL = "https://euw1.api.riotgames.com"
    }

    fun getUserApiFromString(name: String?, imgView: ImageView) {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(ApiRequest::class.java)
        val teamRequest = service.getUser(name, API_KEY)

        teamRequest.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val userTricky = response.body()
                Picasso.get()
                    .load("https://ddragon.leagueoflegends.com/cdn/11.3.1/img/profileicon/${userTricky?.profileIconId}.png")
                    .into(imgView)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("API", "Error : $t")
            }
        })
    }

    fun getUserHistory(name: String?, imgView: ImageView) {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(ApiRequest::class.java)
        val teamRequest = service.getUser(name, API_KEY)

        teamRequest.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val userTricky = response.body()
                Picasso.get()
                    .load("https://ddragon.leagueoflegends.com/cdn/11.3.1/img/profileicon/${userTricky?.profileIconId}.png")
                    .into(imgView)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("API", "Error : $t")
            }
        })
    }
}