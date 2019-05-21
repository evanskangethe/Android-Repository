package com.example.app.ui.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.app.database.Note
import com.example.app.database.NoteRepository
import com.example.app.database.NoteRoomDatabase

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NoteRepository
    val allNotes: LiveData<List<Note>>

    init {
        val noteDao = NoteRoomDatabase.getInstance(application).noteDao()
        repository = NoteRepository(noteDao)
        allNotes = repository.allNotes
    }

    fun insertNote(note: Note) {
        repository.insert(note)
    }

    fun updateNote(note: Note) {
        repository.update(note)
    }

    fun deleteNote(note: Note) {
        repository.delete(note)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }

    fun getAllnotes():LiveData<List<Note>>{
        return allNotes
    }
}
