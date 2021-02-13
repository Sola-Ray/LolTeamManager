package com.fr.iut.pm.teammanager.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.text.format.DateFormat
import android.util.Log
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.fr.iut.pm.teammanager.R
import com.fr.iut.pm.teammanager.api.OnDataLoaded
import com.fr.iut.pm.teammanager.databinding.TeamFragmentBinding
import com.fr.iut.pm.teammanager.model.NEW_TEAM_ID
import com.fr.iut.pm.teammanager.model.User
import com.fr.iut.pm.teammanager.utils.viewModelFactory
import com.fr.iut.pm.teammanager.viewmodel.TeamViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class TeamFragment : Fragment() { //OnDataLoaded

    companion object {
        private const val MY_TEAM_ID = "my_team_id"

        fun newInstance(teamId: Long) = TeamFragment().apply {
            arguments = bundleOf(MY_TEAM_ID to teamId)
        }
    }

    private lateinit var teamVM: TeamViewModel
    private var teamId: Long = NEW_TEAM_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        teamId = savedInstanceState?.getLong(MY_TEAM_ID) ?: arguments?.getLong(MY_TEAM_ID) ?: NEW_TEAM_ID

        if(teamId == NEW_TEAM_ID) {
            requireActivity().title = getString(R.string.add_team)
        }

        setHasOptionsMenu(true)

        teamVM = ViewModelProvider(this, viewModelFactory { TeamViewModel(teamId) }).get()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(MY_TEAM_ID, teamId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val teamBinding = TeamFragmentBinding.inflate(inflater)
        teamBinding.teamVM = teamVM
        teamBinding.lifecycleOwner = viewLifecycleOwner
        return teamBinding.root
    }

    interface OnInteractionListener {
        fun onTeamSaved()
        fun onTeamDeleted()
    }

    private var listener: OnInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context doit implémenter OnInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        if (teamId == NEW_TEAM_ID) {
            menu.findItem(R.id.action_delete)?.isVisible = false
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_team, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                saveTeam()
                true
            }
            R.id.action_delete -> {
                deleteTeam()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveTeam() {
        val loadData = Thread {
            val networkFragment = NetworkFragment()
            teamVM.team.value?.toplaner = networkFragment.getUserFromStringAndApi(teamVM.team.value?.toplaner?.username)
            teamVM.team.value?.jungler = networkFragment.getUserFromStringAndApi(teamVM.team.value?.jungler?.username)
            teamVM.team.value?.midlaner = networkFragment.getUserFromStringAndApi(teamVM.team.value?.midlaner?.username)
            teamVM.team.value?.botlaner = networkFragment.getUserFromStringAndApi(teamVM.team.value?.botlaner?.username)
            teamVM.team.value?.support = networkFragment.getUserFromStringAndApi(teamVM.team.value?.support?.username)
        }

        loadData.start()
        loadData.join()

        if (teamVM.saveTeam() == false) {
            AlertDialog.Builder(requireContext())
                .setTitle(R.string.team_error_title)
                .setMessage(R.string.team_error_message)
                .setNeutralButton(android.R.string.ok, null)
                .show()
            return
        }
        listener?.onTeamSaved()
    }


    private fun deleteTeam() {
        teamVM.deleteTeam()
        listener?.onTeamDeleted()
    }

    /*override fun onSuccess(value: User?) {
        Log.d("test", "onSuccess: $value")
        if(teamVM.team.value?.toplaner?.username.equals(value?.username)) {
            teamVM.team.value?.toplaner = value
        }
        if(teamVM.team.value?.jungler?.username.equals(value?.username)) {
            teamVM.team.value?.jungler = value
        }
        if(teamVM.team.value?.midlaner?.username.equals(value?.username)) {
            teamVM.team.value?.midlaner = value
        }
        if(teamVM.team.value?.botlaner?.username.equals(value?.username)) {
            teamVM.team.value?.botlaner = value
        }
        if(teamVM.team.value?.support?.username.equals(value?.username)) {
            teamVM.team.value?.support = value
        }
    }

    override fun onFailure() {
    }*/
}