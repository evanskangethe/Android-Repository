package com.example.app.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.adapter.NoteAdapter

class NoteHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
    val note_title:TextView = itemView.findViewById(R.id.textview_title)
    val note_priority:TextView = itemView.findViewById(R.id.textview_priority)
    val note_description:TextView = itemView.findViewById(R.id.textview_description)
}