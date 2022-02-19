package com.mobdeve.s11.ruelos.miguel.mc02

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s11.ruelos.miguel.mc02.databinding.ActivityRecordsBinding

class RecordsActivity : AppCompatActivity(){

    lateinit var binding: ActivityRecordsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}