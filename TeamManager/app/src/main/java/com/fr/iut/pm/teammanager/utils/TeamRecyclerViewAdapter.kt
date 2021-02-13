package com.fr.iut.pm.teammanager.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.fr.iut.pm.teammanager.databinding.ItemTeamBinding
import com.fr.iut.pm.teammanager.model.Team

class TeamRecyclerViewAdapter(private val listener: Callbacks) :
    ListAdapter<Team, TeamRecyclerViewAdapter.TeamViewHolder>(TeamDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TeamViewHolder(
        ItemTeamBinding.inflate(LayoutInflater.from(parent.context)), listener
    )

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) =
        holder.bind(getItem(position))

    class TeamViewHolder(private val binding: ItemTeamBinding, listener: Callbacks) : RecyclerView.ViewHolder(binding.root) {

        val team: Team? get() = binding.team

        init {
            itemView.setOnClickListener{
                team?.let { listener.onTeamSelected(it.id) }
            }
        }

        fun bind(team: Team) {
            binding.team = team
            binding.executePendingBindings()
        }
    }

    interface Callbacks {
        fun onTeamSelected(teamId: Long)
    }

    private object TeamDiffCallback : DiffUtil.ItemCallback<Team>() {
        override fun areItemsTheSame(oldItem: Team, newItem: Team) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Team, newItem: Team) = oldItem == newItem
    }
}