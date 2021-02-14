package com.fr.iut.pm.teammanager.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fr.iut.pm.teammanager.R
import com.fr.iut.pm.teammanager.model.MatchEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_match.view.*
import kotlin.math.roundToInt

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
            if(matchEntity.isWinner) {
                itemView.win_or_lose.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.win))
                itemView.cardview.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.win_row_bg))
            } else {
                itemView.win_or_lose.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.lose))
                itemView.cardview.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.lose_row_bg))
            }
            Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/5.16.1/img/champion/"+matchEntity.champName)
                .into(itemView.iv_portrait)
            itemView.tv_type_match.text = matchEntity.typeMatch

            val matchKda = "${matchEntity.kills}/${matchEntity.deaths}/${matchEntity.assists}"
            itemView.tv_kda.text = matchKda

            val matchGold = "${(matchEntity.gold / 1000.0).roundToInt()} K"
            itemView.tv_gold.text = matchGold
            itemView.tv_cs.text = matchEntity.cs.toString()

            itemView.iv_item1.setImageResource(matchEntity.items[0]!!)
            itemView.iv_item2.setImageResource(matchEntity.items[1]!!)
            itemView.iv_item3.setImageResource(matchEntity.items[2]!!)
            itemView.iv_item4.setImageResource(matchEntity.items[3]!!)
            itemView.iv_item5.setImageResource(matchEntity.items[4]!!)
            itemView.iv_item6.setImageResource(matchEntity.items[5]!!)
            itemView.iv_item7.setImageResource(matchEntity.items[6]!!)

            itemView.tv_duration.text = matchEntity.matchDuration.toString()
            itemView.tv_date.text = matchEntity.matchCreation.toString()
        }
    }

    override fun getItemCount() = matchList.size
}