package com.fr.iut.pm.teammanager.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fr.iut.pm.teammanager.R
import com.fr.iut.pm.teammanager.model.MatchEntity

class MatchRecyclerViewAdapter(private val matchList: List<MatchEntity>) :
    RecyclerView.Adapter<MatchRecyclerViewAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MatchViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_match,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) =
        holder.bind(matchList[position])

    class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var matchEntity: MatchEntity? = null
            private set

        fun bind(matchEntity: MatchEntity) {
            this.matchEntity = matchEntity
        }
    }

    override fun getItemCount() = matchList.size
}