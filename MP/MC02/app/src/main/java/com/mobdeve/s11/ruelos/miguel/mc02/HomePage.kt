package com.mobdeve.s11.ruelos.miguel.mc02

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.mobdeve.s11.ruelos.miguel.mc02.databinding.PageHomeBinding
import java.util.*
import kotlin.math.roundToInt

class HomePage : AppCompatActivity(){

    private lateinit var binding : ActivityHomeBinding
    private var timerStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0
    private var userRecords = ArrayList<Record>()
    lateinit var recordAdapter: RecordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var bundle = Bundle()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnstart.setOnClickListener { startStopTimer() }

        binding.btnreset.setOnClickListener { resetTimer() }

        serviceIntent = Intent(applicationContext, TimerService::class.java)
        registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))



        binding.btnrecordrun.setOnClickListener {

            recordAdapter = RecordAdapter(applicationContext, userRecords)

            binding.recordslist.layoutManager = LinearLayoutManager(applicationContext,
                LinearLayoutManager.VERTICAL, false)

            userRecords.add(Record(recordRun(time)))
            binding.recordslist.adapter = recordAdapter
        }

    }

    private fun recordRun(time: Double)  : String{
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(hours, minutes, seconds)

    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent) {
            time = intent.getDoubleExtra(TimerService.TIMER_EXTRA, 0.0)
            binding.timer.text = getTimeStringFromDouble(time)
        }
    }

    private fun getTimeStringFromDouble(time: Double): String {
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(hours, minutes, seconds)
    }

    private fun makeTimeString(hours: Int, minutes: Int, seconds: Int): String = String.format("%02d:%02d:%02d", hours, minutes, seconds)

    private fun resetTimer() {
        stopTimer()
        time = 0.0
        binding.timer.text = getTimeStringFromDouble(time)
    }

    private fun startStopTimer() {
        if(timerStarted)
            stopTimer()
        else
            startTimer()
    }

    private fun startTimer() {
        serviceIntent.putExtra(TimerService.TIMER_EXTRA, time)
        startService(serviceIntent)
        binding.btnstart.text = "Stop"
        timerStarted = true
    }

    private fun stopTimer() {
        stopService(serviceIntent)
        binding.btnstart.text = "Start"
        timerStarted = false
    }


}