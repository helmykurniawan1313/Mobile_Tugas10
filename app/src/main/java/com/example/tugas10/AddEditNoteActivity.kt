package com.example.tugas10

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_note.*
//menghubungkan dengan db
class AddEditNoteActivity : AppCompatActivity() {
    companion object { const val EXTRA_ID = "com.piusanggoro.notesapp.EXTRA_ID"
        const val EXTRA_JUDUL = "com.piusanggoro.notesapp.EXTRA_JUDUL"
        const val EXTRA_DESKRIPSI = "com.piusanggoro.notesapp.EXTRA_DESKRIPSI"
        const val EXTRA_TANGGAL = "com.piusanggoro.notesapp.EXTRA_PRIORITAS"
        const val EXTRA_BULAN = "com.piusanggoro.notesapp.EXTRA_BULAN"
    }
    //menghubungkan dengan activity_add_note.xml
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        number_picker_tanggal.minValue = 1
        number_picker_tanggal.maxValue = 31
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp)
//membuat atau edit catatan
        if (intent.hasExtra(EXTRA_ID)) {
            title = "Edit Catatan"
            edit_text_title.setText(intent.getStringExtra(EXTRA_JUDUL))
            edit_text_description.setText(intent.getStringExtra(EXTRA_DESKRIPSI))
            number_picker_tanggal.value = intent.getIntExtra(EXTRA_TANGGAL, 1)
            edit_text_bulan.setText(intent.getStringExtra(EXTRA_BULAN))
        } else {
            title = "Tambah Catatan"
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }
    //menyimpan note
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.save_note -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    //setting ketika catatan kosong
    private fun saveNote() {
        if (edit_text_title.text.toString().trim().isBlank() || edit_text_description.text.toString().trim().isBlank() || edit_text_bulan.text.toString().trim().isBlank()) {
            Toast.makeText(this, "Catatan kosong!", Toast.LENGTH_SHORT).show()
            return
        }
//menyimpan data ke db
        val data = Intent().apply {
            putExtra(EXTRA_JUDUL, edit_text_title.text.toString())
            putExtra(EXTRA_DESKRIPSI, edit_text_description.text.toString())
            putExtra(EXTRA_TANGGAL, number_picker_tanggal.value)
            putExtra(EXTRA_BULAN, edit_text_bulan.text.toString())
            if (intent.getIntExtra(EXTRA_ID, -1) != -1) {
                putExtra(EXTRA_ID, intent.getIntExtra(EXTRA_ID, -1))
            }
        }
        setResult(Activity.RESULT_OK, data)
        finish()
    }
}