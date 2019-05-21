package com.example.app.database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Note::class],version = 1)
abstract class NoteRoomDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE : NoteRoomDatabase? = null

        fun getInstance(context: Context):NoteRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null ) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteRoomDatabase::class.java,
                    "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(NoteDatabaseCallback())
                    .build()

                INSTANCE = instance

                return instance
            }
        }

        private class NoteDatabaseCallback: RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    PopulateDbAsyncTask(database.noteDao()).execute()
                }
            }
        }

        class PopulateDbAsyncTask(private val noteDao: NoteDao): AsyncTask<Void,Void,Void>(){
            override fun doInBackground(vararg params: Void?): Void? {
                noteDao.deleteAllNotes()
                noteDao.insertNote(Note(title = "Title 1",description = "Description 1", priority = 1))
                noteDao.insertNote(Note(title = "Title 2",description = "Description 2", priority = 2))
                noteDao.insertNote(Note(title = "Title 3",description = "Description 3", priority = 3))
                return null
            }

        }
    }
}