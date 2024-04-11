package com.example.notesexp2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notesexp2.model.Notes

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: Notes)

    @Update
    suspend fun updateNotes(notes: Notes)

    @Query("SELECT * FROM Notes WHERE priority=1 ")
    fun getLowNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=3 ")
    fun getMediumNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=2 ")
    fun getHighNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM Notes")
    fun getNotes(): LiveData<List<Notes>>

    @Query("DELETE FROM Notes WHERE id =:id")
    suspend fun deleteNotes(id: Int)
}