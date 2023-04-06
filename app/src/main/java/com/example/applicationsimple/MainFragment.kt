package com.example.applicationsimple

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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
import com.example.applicationsimple.data.models.BankDepartment
import com.example.applicationsimple.data.repositories.BankRepositoryImpl

class MainFragment : Fragment() {
    private val viewModel by viewModels<MainViewModel> {
        RepositoryViewModelFactory(
            BankRepositoryImpl()
        )
    }
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var displayedMessage: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var bankAdapter: BankAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayedMessage = view.findViewById(R.id.displayed_message)
        autoCompleteTextView = view.findViewById(R.id.dropdown_menu)
        recyclerView = view.findViewById(R.id.recyclerview)
        bankAdapter = BankAdapter()
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = bankAdapter
        citiesSelectorSetup()
        observeBanks()
    }

    private fun citiesSelectorSetup() {
        autoCompleteTextView.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.drop_down_item,
                resources.getStringArray(R.array.cities)
            )
        )
        autoCompleteTextView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                if (checkConnection()) {
                    viewModel.getBanksInCity((view as TextView).text.toString())
                    displayMessage(resources.getString(R.string.waiting_message))
                } else {
                    displayMessage(resources.getString(R.string.no_internet_message))
                }
            }
    }
    private fun displayBanks(banks: List<BankDepartment>) {
        if (banks.isNotEmpty()) {
            bankAdapter.setBanks(banks)
            recyclerView.visibility = View.VISIBLE
            displayedMessage.visibility = View.GONE
        } else {
            displayMessage(resources.getString(R.string.timeout_error))
        }
    }
    private fun displayMessage(message: String) {
        displayedMessage.text = message
        displayedMessage.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }
    private fun observeBanks() {
        viewModel.observableBanks.observe(viewLifecycleOwner) {
            displayBanks(it)
        }
    }
    private fun checkConnection(): Boolean {
        var result = false
        context?.let {
            val connectivityManager =
                it.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkCapabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            result =
                networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }
        return result
    }
}