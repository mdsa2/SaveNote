package com.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.Database.NotesDatabase
import com.Repository.Notesrepository
import com.model.Notes

class NotesView(application: Application) : AndroidViewModel(  application) {
    val repositrory: Notesrepository
    init {
        val dao =NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repositrory=Notesrepository(dao)
    }
    fun addNotes(notes:Notes)
    {
        repositrory.insertNotes(notes)

    }

    fun getNotes():LiveData<List<Notes>> = repositrory.getAllNotes()
    fun getLowNotes():LiveData<List<Notes>> = repositrory.getLowNotes()
    fun getMediumNotes():LiveData<List<Notes>> = repositrory.getMediumNotes()
    fun getHighNotes():LiveData<List<Notes>> = repositrory.getHighNotes()

    fun deletNotes(id:Int){
        repositrory.deletNotes(id)
    }
    fun updateNotes(notes:Notes)
    {
      repositrory.UpdateNotes(notes)
    }













}