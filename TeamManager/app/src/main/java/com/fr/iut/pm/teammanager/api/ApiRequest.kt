package com.fr.iut.pm.teammanager.api
import com.fr.iut.pm.teammanager.model.User
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequest {
    @GET("/lol/summoner/v4/summoners/by-name/Minyan%20Chan")
    fun getUser(): Call<User>
}