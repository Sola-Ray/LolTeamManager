package com.fr.iut.pm.teammanager.fragment

import android.util.Log
import com.fr.iut.pm.teammanager.api.ApiRequest
import com.fr.iut.pm.teammanager.model.MatchEntity
import com.fr.iut.pm.teammanager.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class NetworkFragment {

    companion object {
        private const val API_KEY = "RGAPI-de8c1f1e-42a7-4148-a9ae-3228ca289fc6"
        private const val URL = "https://euw1.api.riotgames.com"
    }

    /**
     * Requête permettant de récupérer un User de l'API depuis un String donné
     */
    fun getUserFromStringAndApi(name: String?): User {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(ApiRequest::class.java)
        val teamRequest = service.getUser(name, API_KEY)

        return teamRequest.execute().body() ?: User("No User Found", 0, "", "")
    }

    /**
     * Récupère la liste des matchs depuis l'API à l'aide de l'ID du compte
     */
    fun getUserHistory(accountId: String?) {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(ApiRequest::class.java)
        val teamRequest = service.getHistory(accountId, API_KEY)

        teamRequest.enqueue(object : Callback<List<MatchEntity>> {
            override fun onResponse(call: Call<List<MatchEntity>>, response: Response<List<MatchEntity>>) {
                val matchEntity = response.body()
                Log.d("test", "onResponse: $matchEntity")
            }

            override fun onFailure(call: Call<List<MatchEntity>>, t: Throwable) {
                Log.e("API", "Error : $t")
            }
        })
    }
}