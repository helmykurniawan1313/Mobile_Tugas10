package com.example.tugas10.note

import androidx.room.Entity
import androidx.room.PrimaryKey
//deklarasi data
@Entity(tableName = "note_table")
data class Note(
    var title: String,
    var deskripsi: String,
    var tanggal: Int,
    var bulan : String) {

    @PrimaryKey(autoGenerate = true) var id: Int = 0
}