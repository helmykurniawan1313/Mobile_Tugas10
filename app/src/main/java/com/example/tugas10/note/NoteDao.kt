package com.example.tugas10.note

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    //deklarasi insert untuk mamasukkan data pada db
    @Insert
    fun insert(note: Note)
    //deklarasi update untuk mengubah data pada db
    @Update
    fun update(note: Note)
    //deklarasi delete untuk menghapus data pada db
    @Delete
    fun delete(note: Note)
    //membuat query untuk hapus
    @Query("DELETE FROM note_table")
    fun deleteAllNotes()
    //query untuk mengambil semua data
    @Query("SELECT * from note_table")
    fun getAllNotes(): LiveData<List<Note>>
}
