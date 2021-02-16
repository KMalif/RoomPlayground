package com.example.roomplayground.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomplayground.databinding.NotesItemBinding
import com.example.roomplayground.room.Note

class NoteAdapter(private val notes : ArrayList<Note>, private val listener : onAdapterListener):RecyclerView.Adapter<NoteAdapter.noteViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): noteViewHolder {
        return noteViewHolder(NotesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: noteViewHolder, position: Int) {
        val note = notes[position]
        holder.binding.tvTitle.text = note.title
        holder.itemView.setOnClickListener {
            listener.onClick(note)
        }

    }

    class noteViewHolder (val binding: NotesItemBinding):RecyclerView.ViewHolder(binding.root)

    fun setData(list : List<Note>){
        notes.clear()
        notes.addAll(list)
        notifyDataSetChanged()
    }

    interface onAdapterListener{
        fun onClick(note : Note)
    }
}