package com.example.firebasebeta.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasebeta.Models.DatabaseModel
import com.example.firebasebeta.databinding.ActivityInsertionDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertionData : AppCompatActivity() {
    private lateinit var name : EditText
    private lateinit var lastname : EditText
    private lateinit var salary : EditText
    private lateinit var savebtn : Button

    private lateinit var dbref: DatabaseReference

    private lateinit var binding: ActivityInsertionDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding =ActivityInsertionDataBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        name =binding.editText1
        lastname=binding.editText2
        salary= binding.editText3
        savebtn = binding.savebtn

        dbref = FirebaseDatabase.getInstance().getReference("Empdatabase")


        binding.savebtn.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val editText1 = name.text.toString()
        val editText2 = lastname.text.toString()
        val editText3 = salary.text.toString()

        if (editText1.isEmpty()){
            Toast.makeText(this,"Enter a Name",Toast.LENGTH_LONG).show()
        }
        if (editText2.isEmpty()){
            Toast.makeText(this,"Enter a Last Name",Toast.LENGTH_LONG).show()
        }
        if (editText3.isEmpty()){
            Toast.makeText(this,"Enter a Salary",Toast.LENGTH_LONG).show()
        }
        val empId = dbref.push().key!!
        val Empdatabase = DatabaseModel(empId, editText1, editText2, editText3)
        dbref.child(empId).setValue(Empdatabase)
            .addOnCompleteListener {
                Toast.makeText(this, "Data Saved", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Data Error",Toast.LENGTH_LONG).show()
            }
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }


}