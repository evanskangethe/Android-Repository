package com.example.app.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.database.Note
import com.example.app.extension.inflate
import com.example.app.holder.NoteHolder

class NoteAdapter: RecyclerView.Adapter<NoteHolder>(){
    private var notes = emptyList<Note>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val inflatedView = parent.inflate(R.layout.note_item,false)
        return NoteHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentNote = notes[position]
        holder.note_title.text = currentNote.title
        holder.note_description.text = currentNote.description
        holder.note_priority.text = currentNote.priority.toString()
    }

    internal fun setNotes(note: List<Note>) {
        this.notes = note
        notifyDataSetChanged()
    }

    internal fun getNoteAt(position: Int): Note {
        return notes[position]
    }

}