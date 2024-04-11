package com.example.notesexp2.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notesexp2.database.NotesDatabase
import com.example.notesexp2.database.NotesRepository
import com.example.notesexp2.model.Notes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val repository: NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).notesDao
        repository = NotesRepository(dao)
    }

    fun addNotes(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertNotes(notes)
    }

    fun getLowNotes(): LiveData<List<Notes>> = repository.getLowNotes()
    fun getMediumNotes(): LiveData<List<Notes>> = repository.getMediumNotes()
    fun getHighNotes(): LiveData<List<Notes>> = repository.getHighNotes()

    fun getALlNotes():LiveData<List<Notes>> = repository.getAllNotes()

    fun deleteNotes(id:Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteNotes(id)
    }
    fun updateNotes(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateNotes(notes)
    }
}