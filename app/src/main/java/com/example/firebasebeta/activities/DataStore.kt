package com.example.firebasebeta.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasebeta.Adapters.DataAdapter
import com.example.firebasebeta.Models.DatabaseModel
import com.example.firebasebeta.R
import com.example.firebasebeta.databinding.ActivityDataStoreBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Objects

class DataStore : AppCompatActivity() {
    private lateinit var rcView: RecyclerView
    private lateinit var txtData :TextView
    private lateinit var itemView : TextView
    private lateinit var binding: ActivityDataStoreBinding
    private lateinit var empList: ArrayList<DatabaseModel>
    private lateinit var dbref : DatabaseReference
private lateinit var statusbar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityDataStoreBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root )
        rcView = binding.recyclerView
        txtData = binding.itemView
        itemView = binding.itemView
        statusbar = binding.statusbar


        rcView.layoutManager=LinearLayoutManager(this)



        getEmployedata()
    }

    private fun getEmployedata() {
        rcView.visibility = View.GONE
        itemView.visibility = View.VISIBLE
        statusbar.visibility = View.VISIBLE
        empList= arrayListOf<DatabaseModel>()


        fetch()

    }

    private fun fetch() {
        dbref = FirebaseDatabase.getInstance().getReference("Empdatabase")
        dbref.addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){
                    for (empsnap in snapshot.children){
                        val empData = empsnap.getValue(DatabaseModel::class.java)
                        empList.add(empData!!)
                    }
                    val madapter = DataAdapter(empList)
                    rcView.adapter = madapter
                    rcView.visibility = View.VISIBLE
                    itemView.visibility = View.GONE
                    statusbar.visibility= View.GONE
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(this.toString(),"Error")
            }
        })
    }

}

