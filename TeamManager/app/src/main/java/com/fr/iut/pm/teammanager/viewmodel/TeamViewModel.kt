package com.fr.iut.pm.teammanager.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fr.iut.pm.teammanager.data.persistance.TeamDatabase
import com.fr.iut.pm.teammanager.data.persistance.TeamRepository
import com.fr.iut.pm.teammanager.model.NEW_TEAM_ID
import com.fr.iut.pm.teammanager.model.Team
import com.fr.iut.pm.teammanager.model.User
import kotlinx.coroutines.launch

class TeamViewModel(teamId: Long) : ViewModel() {
    private val teamRepo = TeamRepository(TeamDatabase.getInstance().teamDAO())

    val team = if (teamId == NEW_TEAM_ID) MutableLiveData(Team()) else teamRepo.findById(teamId)

    fun saveTeam() = team.value?.let {
        if (it.name.isBlank())
            false
        else {
            viewModelScope.launch {
                if (it.id == NEW_TEAM_ID) teamRepo.insert(it) else teamRepo.update(it)
            }
            true
        }
    }

    fun deleteTeam() = viewModelScope.launch {
        team.value?.let { if (it.id != NEW_TEAM_ID) teamRepo.delete(it) }
    }
}