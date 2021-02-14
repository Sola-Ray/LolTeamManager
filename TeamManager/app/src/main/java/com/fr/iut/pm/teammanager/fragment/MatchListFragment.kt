package com.fr.iut.pm.teammanager.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.fr.iut.pm.teammanager.R
import com.fr.iut.pm.teammanager.model.MatchEntity
import com.fr.iut.pm.teammanager.utils.MatchRecyclerViewAdapter
import kotlinx.android.synthetic.main.match_list_fragment.view.*

class MatchListFragment : Fragment() {

    private var matchList = ArrayList<MatchEntity>()
    private val matchListAdapter =
        MatchRecyclerViewAdapter(matchList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.match_list_fragment, container, false)
        view.recycler_view_match.adapter = matchListAdapter
        return view
    }
}
