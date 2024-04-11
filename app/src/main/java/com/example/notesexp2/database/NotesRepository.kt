package com.example.notesexp2.database

import androidx.lifecycle.LiveData
import com.example.notesexp2.model.Notes

class NotesRepository(val dao: NotesDao) {

    fun getAllNotes(): LiveData<List<Notes>> = dao.getNotes()
    fun getLowNotes(): LiveData<List<Notes>> = dao.getLowNotes()
    fun getMediumNotes(): LiveData<List<Notes>> = dao.getMediumNotes()
    fun getHighNotes(): LiveData<List<Notes>> = dao.getHighNotes()
    suspend fun insertNotes(notes: Notes) = dao.insertNotes(notes)
    suspend fun deleteNotes(id: Int) = dao.deleteNotes(id)
    suspend fun updateNotes(notes: Notes) = dao.updateNotes(notes)

}