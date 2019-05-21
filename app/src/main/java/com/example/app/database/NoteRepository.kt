package com.example.app.database

import android.os.AsyncTask
import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    fun insert(note: Note) {
        InsertNoteAsyncTask(noteDao).execute(note)
    }

    fun update(note: Note) {
        UpdateNoteAsyncTask(noteDao).execute(note)
    }

    fun delete(note: Note) {
        DeleteNoteAsyncTask(noteDao).execute(note)
    }

    fun deleteAllNotes() {
        DeleteAllNoteAsyncTask(noteDao).execute()
    }

    fun getNotes():LiveData<List<Note>> {
        return allNotes
    }

    class InsertNoteAsyncTask(private val noteDao: NoteDao): AsyncTask<Note,Void,Void>() {
        override fun doInBackground(vararg params: Note): Void? {
            noteDao.insertNote(params[0])
            return null
        }

    }

    class UpdateNoteAsyncTask(private val noteDao: NoteDao): AsyncTask<Note,Void,Void>() {
        override fun doInBackground(vararg params: Note): Void? {
            noteDao.updateNote(params[0])
            return null
        }

    }

    class DeleteNoteAsyncTask(private val noteDao: NoteDao): AsyncTask<Note,Void,Void>() {
        override fun doInBackground(vararg params: Note): Void? {
            noteDao.deleteNote(params[0])
            return null
        }

    }

    class DeleteAllNoteAsyncTask(private val noteDao: NoteDao): AsyncTask<Void,Void,Void>() {
        override fun doInBackground(vararg params: Void): Void? {
            noteDao.deleteAllNotes()
            return null
        }

    }
}