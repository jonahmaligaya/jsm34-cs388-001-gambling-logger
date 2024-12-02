package com.example.GamblingLogger

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WagerListFragment : Fragment() {
    private val wager = mutableListOf<Wager>()
    private lateinit var wagerRecyclerView: RecyclerView
    private lateinit var wagerAdapter: WagerAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.wager_fragment_list, container, false)
        val layoutManager = LinearLayoutManager(context)
        wagerRecyclerView = view.findViewById(R.id.wager_recycler_view)
        wagerRecyclerView.setHasFixedSize(true)
        wagerAdapter = WagerAdapter(view.context, wager) {wagerTitle -> deleteWager(wagerTitle)}

        lifecycleScope.launch {
            (activity?.application as GamblingLoggerApp).db.wagerDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    Wager(entity.profitOrLoss, entity.wagerOdds, entity.wagerTitle)
                }.also { mappedList ->
                    wager.clear()
                    wager.addAll(mappedList)
                    wagerAdapter.notifyDataSetChanged()

                }
            }
        }

        wagerRecyclerView.adapter = wagerAdapter

        layoutManager.also {
            val dividerItemDecoration = DividerItemDecoration(context, it.orientation)
            wagerRecyclerView.addItemDecoration(dividerItemDecoration)
        }
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    private fun deleteWager(title: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            (activity?.application as GamblingLoggerApp).db.wagerDao().deleteWager(title)
        }
    }
    companion object {
        fun newInstance(): WagerListFragment{
            return WagerListFragment()
        }
    }
}