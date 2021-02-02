package com.fr.iut.pm.teammanager.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fr.iut.pm.teammanager.R
import com.fr.iut.pm.teammanager.data.persistance.TeamDatabase
import com.fr.iut.pm.teammanager.model.Team
import com.fr.iut.pm.teammanager.utils.TeamRecyclerViewAdapter
import kotlinx.android.synthetic.main.team_list_fragment.*
import kotlinx.android.synthetic.main.team_list_fragment.view.*
import java.lang.RuntimeException

class TeamListFragment : Fragment(), TeamRecyclerViewAdapter.Callbacks {
    private var teamList = TeamDatabase.getInstance().teamDAO().getAll()

    private val teamListAdapter = TeamRecyclerViewAdapter(teamList, this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.team_list_fragment, container, false)
        view.recycler_view.adapter = teamListAdapter
        view.group_empty_view.visibility = if (teamList.isEmpty()) View.VISIBLE else View.GONE
        view.fab.setOnClickListener{ addNewTeam() }
        return view
    }

    private var listener: OnInteractionListener? = null

    private fun addNewTeam() {
        listener?.onAddNewTeam()
    }

    override fun onTeamSelected(teamId: Long) {
        listener?.onTeamSelected(teamId)
    }

    interface OnInteractionListener {
        fun onTeamSelected(teamId: Long)
        fun onAddNewTeam()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context doit implémenter OnInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onResume() {
        super.onResume()
        updateList()
    }

    private fun updateList() {
        teamList = TeamDatabase.getInstance().teamDAO().getAll()
        teamListAdapter.updateList(teamList)
        group_empty_view.visibility = if (teamList.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun removeTeam(team: Team) {
        val dao = TeamDatabase.getInstance().teamDAO()
        dao.delete(team)
        updateList()
    }

}
