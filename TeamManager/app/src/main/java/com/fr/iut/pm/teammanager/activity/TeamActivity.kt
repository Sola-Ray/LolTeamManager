package com.fr.iut.pm.teammanager.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fr.iut.pm.teammanager.R
import com.fr.iut.pm.teammanager.api.ApiRequest
import com.fr.iut.pm.teammanager.fragment.TeamFragment
import com.fr.iut.pm.teammanager.model.NEW_TEAM_ID
import com.fr.iut.pm.teammanager.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TeamActivity : AppCompatActivity(), TeamFragment.OnInteractionListener {

    companion object {
        private const val MY_TEAM_ID = "my_team_id"

        fun getIntent(context: Context, teamId: Long) =
            Intent(context, TeamActivity::class.java).apply {
                putExtra(MY_TEAM_ID, teamId)
            }
    }

    private var teamId = NEW_TEAM_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        teamId = intent.getLongExtra(MY_TEAM_ID, NEW_TEAM_ID)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if(supportFragmentManager.findFragmentById(R.id.container) == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, createFragment())
                .commitNow()
        }

        //testApi()
    }

    fun createFragment() = TeamFragment.newInstance(teamId)

    override fun onTeamSaved() = finish()

    override fun onTeamDeleted() = finish()

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