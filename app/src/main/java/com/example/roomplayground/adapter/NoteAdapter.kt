package com.example.roomplayground.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomplayground.R
import com.example.roomplayground.room.Note
import kotlinx.android.synthetic.main.notes_item.view.*

class NoteAdapter(private val notes : ArrayList<Note>):RecyclerView.Adapter<NoteAdapter.noteViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): noteViewHolder {
        return noteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.notes_item, parent, false))
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: noteViewHolder, position: Int) {
        val note = notes[position]
        holder.view.tvTitle.text = note.title

    }

    class noteViewHolder (val view: View):RecyclerView.ViewHolder(view)

    fun setData(list : List<Note>){
        notes.clear()
        notes.addAll(list)
        notifyDataSetChanged()
    }
}