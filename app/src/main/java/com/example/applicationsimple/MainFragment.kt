package com.example.applicationsimple

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.applicationsimple.Data.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        val autoCompleteTextView: AutoCompleteTextView = view.findViewById(R.id.dropdown_menu)
        autoCompleteTextView.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.drop_down_item,
                resources.getStringArray(R.array.cities)
            )
        )
        autoCompleteTextView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                CoroutineScope(Dispatchers.IO).launch {
                    val banks = RetrofitService.belarusbankAPI.getBanksInCity((view as TextView).text.toString())
                    activity?.let {
                        it.runOnUiThread {
                            Toast.makeText(context,"Success", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
        return view
    }
}