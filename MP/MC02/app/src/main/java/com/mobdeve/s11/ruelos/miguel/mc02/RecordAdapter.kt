package com.mobdeve.s11.ruelos.miguel.mc02

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mobdeve.s11.ruelos.miguel.mc02.databinding.RvRecordsBinding
import com.mobdeve.s11.ruelos.miguel.mc02.Record

class RecordAdapter (private val recordList: ArrayList<Record>)
    :RecyclerView.Adapter<RecordAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordAdapter.ViewHolder {
        val recordBinding = RvRecordsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(recordBinding)
    }

    class ViewHolder(private val recordBinding : RvRecordsBinding)
        : RecyclerView.ViewHolder(recordBinding.root){

        fun bindRecordSave(userRecord: Record){
            recordBinding.timer.text = "${userRecord.hr}:${userRecord.min}:${userRecord.sec}"
        }
    }
    override fun getItemCount() = recordList.size

    override fun onBindViewHolder(holder: RecordAdapter.ViewHolder, position: Int) {
        holder.bindRecordSave(recordList[position])
        holder.itemView.findViewById<Button>(R.id.buttondelete).setOnClickListener {
            recordList.removeAt(position)
            notifyItemRemoved(position)

            notifyDataSetChanged()
        }
    }


}