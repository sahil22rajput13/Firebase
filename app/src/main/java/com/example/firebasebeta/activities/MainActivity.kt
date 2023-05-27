 package com.example.firebasebeta.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasebeta.databinding.ActivityMainBinding

 class MainActivity : AppCompatActivity() {
     private lateinit var binding: ActivityMainBinding
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         binding = ActivityMainBinding.inflate(layoutInflater)
         setContentView(binding.root)
         getdata()
    }

     private fun getdata() {

         binding.insertbtn.setOnClickListener {
             intent = Intent(this, InsertionData::class.java)
             startActivity(intent)
         }
         binding.updatebtn.setOnClickListener {
             intent= Intent(this, DataStore::class.java)
             startActivity(intent)
         }
     }
 }