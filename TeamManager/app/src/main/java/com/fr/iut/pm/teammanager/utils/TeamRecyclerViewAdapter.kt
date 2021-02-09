package com.fr.iut.pm.teammanager.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fr.iut.pm.teammanager.R
import com.fr.iut.pm.teammanager.fragment.NetworkFragment
import com.fr.iut.pm.teammanager.model.Team
import kotlinx.android.synthetic.main.item_team.view.*
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class TeamRecyclerViewAdapter(private var teamList: List<Team>, private val listener: Callbacks) : RecyclerView.Adapter<TeamRecyclerViewAdapter.TeamViewHolder>() {

    private val lstTeams: List<Team>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TeamViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_team,
            parent,
            false
        ), listener
    )

    override fun getItemCount() = teamList.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) =
        holder.bind(teamList[position])

    class TeamViewHolder(itemView: View, listener: Callbacks) : RecyclerView.ViewHolder(itemView) {

        var team: Team? = null
            private set

        init {
            itemView.setOnClickListener{
                team?.let { listener.onTeamSelected(it.id) }
            }
        }

        fun bind(team: Team) {
            this.team = team
            itemView.view_team_name.text = team.name
            itemView.view_user_adc.text = team.botlaner?.username ?: ""
            itemView.view_user_top.text = team.toplaner?.username ?: ""
            itemView.view_user_jgl.text = team.jungler?.username ?: ""
            itemView.view_user_mid.text = team.midlaner?.username ?: ""
            itemView.view_user_bot.text = team.support?.username ?: ""

            val networkFragment = NetworkFragment()
            networkFragment.getUserApiFromString(team.botlaner?.username, itemView.img_user_adc)
            networkFragment.getUserApiFromString(team.toplaner?.username, itemView.img_user_top)
            networkFragment.getUserApiFromString(team.jungler?.username, itemView.img_user_jgl)
            networkFragment.getUserApiFromString(team.midlaner?.username, itemView.img_user_mid)
            networkFragment.getUserApiFromString(team.support?.username, itemView.img_user_sup)
        }
    }

    fun updateList(teamList: List<Team>) {
        this.teamList = teamList
        notifyDataSetChanged()
    }

    interface Callbacks {
        fun onTeamSelected(teamId: Long)
    }
}