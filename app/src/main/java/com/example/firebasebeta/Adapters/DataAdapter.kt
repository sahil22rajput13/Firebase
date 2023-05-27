package com.example.firebasebeta.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.firebasebeta.Models.DatabaseModel
import com.example.firebasebeta.R
import kotlinx.coroutines.NonDisposableHandle
import kotlinx.coroutines.NonDisposableHandle.parent


class DataAdapter (private val emplist : ArrayList<DatabaseModel>): RecyclerView.Adapter<DataAdapter
.ViewHolder>() {




    class ViewHolder (items: View):RecyclerView.ViewHolder(items) {
        val empName :TextView= itemView.findViewById(R.id.empfirst)
        val empLast :TextView= itemView.findViewById(R.id.emplast)
        val empSalary :TextView= itemView.findViewById(R.id.empsalary)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataAdapter.ViewHolder, position: Int) {
        holder.empName.text = emplist[position].editText1
        holder.empLast.text = emplist[position].editText2
        holder.empSalary.text = emplist[position].editText3
    }


    override fun getItemCount(): Int {
       return emplist.size

    }



}
