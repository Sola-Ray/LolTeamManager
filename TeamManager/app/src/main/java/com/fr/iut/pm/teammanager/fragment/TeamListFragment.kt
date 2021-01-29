package com.fr.iut.pm.teammanager.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fr.iut.pm.teammanager.R
import com.fr.iut.pm.teammanager.data.persistance.TeamDatabase
import com.fr.iut.pm.teammanager.utils.TeamRecyclerViewAdapter
import kotlinx.android.synthetic.main.team_list_fragment.view.*
import java.lang.RuntimeException

class TeamListFragment : Fragment(), TeamRecyclerViewAdapter.Callbacks {
    private var teamList = TeamDatabase.getInstance().teamDAO().getAll()

    private var teamListAdapter = TeamRecyclerViewAdapter(teamList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setHasOptionsMenu(true) (à tester)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.team_list_fragment, container, false)
        view.recycler_view.adapter = teamListAdapter

        /* à tester */
        view.group_empty_view.visibility = if (teamList.isEmpty()) View.VISIBLE else View.GONE
        // jusque là
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

}
