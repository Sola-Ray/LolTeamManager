package com.fr.iut.pm.teammanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fr.iut.pm.teammanager.api.ApiRequest
import com.fr.iut.pm.teammanager.fragment.NetworkFragment
import com.fr.iut.pm.teammanager.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        testApi()
    }

    private val url = "https://euw1.api.riotgames.com/"

    fun testApi() {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(ApiRequest::class.java)
        val teamRequest = service.getUser()

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