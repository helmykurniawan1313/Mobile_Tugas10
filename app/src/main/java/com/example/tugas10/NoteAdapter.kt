package com.example.tugas10

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tugas10.note.Note
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter : ListAdapter<Note, NoteAdapter.NoteHolder>(DIFF_CALLBACK) {
    companion object {
        //setting ketika item sama
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.title == newItem.title && oldItem.deskripsi == newItem.deskripsi && oldItem.tanggal == newItem.tanggal && oldItem.bulan == newItem.bulan
            }
        }
    }
    //menghubungkan dengan note_item.xml
    private var listener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteHolder(itemView)
    }
    //membuat holder dan deklarasi data agar data dari db bisa di grab dan ditampilkan pada xml
    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentNote: Note = getItem(position)
        holder.textViewTitle.text = currentNote.title
        holder.textViewTanggal.text = currentNote.tanggal.toString()
        holder.textViewDescription.text = currentNote.deskripsi
        holder.textViewBulan.text = currentNote.bulan
    }
    fun getNoteAt(position: Int): Note {
        return getItem(position)
    }
    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }
        var textViewTitle: TextView = itemView.text_view_title
        var textViewTanggal: TextView = itemView.text_view_tanggal
        var textViewDescription: TextView = itemView.text_view_description
        var textViewBulan: TextView = itemView.text_view_bulan
    }
    interface OnItemClickListener {
        fun onItemClick(note: Note)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}