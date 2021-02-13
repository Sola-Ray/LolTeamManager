package com.fr.iut.pm.teammanager.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fr.iut.pm.teammanager.databinding.TeamListFragmentBinding
import com.fr.iut.pm.teammanager.model.Team
import com.fr.iut.pm.teammanager.utils.TeamRecyclerViewAdapter
import com.fr.iut.pm.teammanager.viewmodel.TeamListViewModel
import java.lang.RuntimeException

class TeamListFragment : Fragment(), TeamRecyclerViewAdapter.Callbacks {
    private val teamListVM by viewModels<TeamListViewModel>()

    private val teamListAdapter = TeamRecyclerViewAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewBinding = TeamListFragmentBinding.inflate(inflater)
        viewBinding.teamListVM = teamListVM
        viewBinding.lifecycleOwner = viewLifecycleOwner

        viewBinding.recyclerView.adapter = teamListAdapter

        teamListVM.teamList.observe(viewLifecycleOwner) {
            teamListAdapter.submitList(it)
        }

        viewBinding.fab.setOnClickListener { addNewTeam() }

        return viewBinding.root
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

    private fun removeTeam(team: Team) {
        teamListVM.deleteTeam(team)
    }

}
