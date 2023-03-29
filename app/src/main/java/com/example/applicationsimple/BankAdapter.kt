package com.example.applicationsimple

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationsimple.Data.BankDepartment

class BankAdapter : RecyclerView.Adapter<BankAdapter.BankHolder>() {
    private var banks = ArrayList<BankDepartment>()
    inner class BankHolder(view: View) : RecyclerView.ViewHolder(view) {
        val USDBuyTextview: TextView = view.findViewById(R.id.usd_buy_textview)
        val USDSaleTextview: TextView = view.findViewById(R.id.usd_sale_textview)
        val EURBuyTextview: TextView = view.findViewById(R.id.eur_buy_textview)
        val EURSaleTextview: TextView = view.findViewById(R.id.eur_sale_textview)
        val RUBBuyTextview: TextView = view.findViewById(R.id.rub_buy_textview)
        val RubSaleTextview: TextView = view.findViewById(R.id.rub_sale_textview)
        val descriptionTextView: TextView = view.findViewById(R.id.department_description)
    }

    fun setBanks(banks:List<BankDepartment>) {
        this.banks = banks as ArrayList<BankDepartment>
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.department_layout, parent, false)
        return BankHolder(view)
    }

    override fun onBindViewHolder(holder: BankHolder, position: Int) {
        val department = banks[position]
        holder.apply {
            USDBuyTextview.text = department.USD_in
            USDSaleTextview.text = department.USD_out
            EURBuyTextview.text = department.EUR_in
            EURSaleTextview.text = department.EUR_out
            RUBBuyTextview.text = department.RUB_in
            RubSaleTextview.text = department.RUB_out
            descriptionTextView.text = "${department.street_type} ${department.street}, д ${department.home_number}"
        }
    }

    override fun getItemCount() = banks.size


}