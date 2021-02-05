package com.fr.iut.pm.teammanager.api
import com.fr.iut.pm.teammanager.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {
    @GET("/lol/summoner/v4/summoners/by-name/Minyan%20Chan")
    fun getUser(@Query("RGAPI-c53f4941-a1c5-4b09-9580-44702dcdb545") key: String): Call<User>
}