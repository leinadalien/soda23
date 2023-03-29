package com.example.applicationsimple

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainFragment : Fragment() {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        val displayedMessage: TextView = view.findViewById(R.id.displayed_message)
        val autoCompleteTextView: AutoCompleteTextView = view.findViewById(R.id.dropdown_menu)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerview)
        val bankAdapter = BankAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = bankAdapter
        viewModel.observableBanks.observe(viewLifecycleOwner) {
            bankAdapter.setBanks(it)
            recyclerView.visibility = View.VISIBLE
            displayedMessage.visibility = View.GONE
        }
        autoCompleteTextView.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.drop_down_item,
                resources.getStringArray(R.array.cities)
            )
        )
        autoCompleteTextView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view_arg, position, id ->
                viewModel.getBanksInCity((view_arg as TextView).text.toString())
                displayedMessage.text = resources.getString(R.string.waiting_message)
                displayedMessage.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
        return view
    }
}