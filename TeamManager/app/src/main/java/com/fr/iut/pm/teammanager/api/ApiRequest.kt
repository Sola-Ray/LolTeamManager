package com.fr.iut.pm.teammanager.api
import com.fr.iut.pm.teammanager.model.MatchEntity
import com.fr.iut.pm.teammanager.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRequest {

    @GET("/lol/summoner/v4/summoners/by-name/{user}")
    fun getUser(@Path("user") user: String?, @Query("api_key") key: String): Call<User>

    @GET("lol/match/v4/matchlists/by-account/{accountId}")
    fun getHistory(@Path("accountId") accountId: String?, @Query("api_key") key: String): Call<List<MatchEntity>>
}