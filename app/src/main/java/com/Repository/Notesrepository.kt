package com.Repository

import androidx.lifecycle.LiveData
import com.Dao.NotesDao
import com.model.Notes

class Notesrepository(val dao:NotesDao) {
    fun getAllNotes():LiveData<List<Notes>> = dao.getNotes()
    fun getLowNotes():LiveData<List<Notes>> = dao.getLowNotes()
    fun getMediumNotes():LiveData<List<Notes>> = dao.getMediumNotes()
    fun getHighNotes():LiveData<List<Notes>> = dao.getHighNotes()

    fun insertNotes(notes:Notes){
        dao.insertNotes(notes)
    }
    fun deletNotes(id:Int){
        dao.deletNotes(id)
    }
    fun UpdateNotes(notes: Notes){
        dao.updateNotes(notes)
    }
}