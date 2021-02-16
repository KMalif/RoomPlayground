 package com.example.roomplayground

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomplayground.adapter.NoteAdapter
import com.example.roomplayground.databinding.ActivityMainBinding
import com.example.roomplayground.room.Constant
import com.example.roomplayground.room.Note
import com.example.roomplayground.room.NoteDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

 class MainActivity : AppCompatActivity() {
     private val db by lazy { NoteDB(this) }
     private lateinit var noteAdapter: NoteAdapter
     private lateinit var binding: ActivityMainBinding

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListener()
        setupRecycler()
    }

     override fun onStart() {
         super.onStart()
         CoroutineScope(Dispatchers.IO).launch {
             val notes = db.noteDao().getNotes()
             Log.d("MainActivity", "dbresponse $notes")
             withContext(Dispatchers.Main){
                 noteAdapter.setData(notes)
             }
         }
     }

    private fun setupListener(){
        binding.fab.setOnClickListener {
            intentEdit(0,Constant.TYPE_CREATE)
        }
    }
     private fun intentEdit(noteId : Int , intentType : Int){
        startActivity(
                Intent(applicationContext, EditActivity::class.java)
                        .putExtra("intent_id", noteId)
                        .putExtra("intent_type", intentType)
        )
     }

     private fun setupRecycler(){
        noteAdapter = NoteAdapter(arrayListOf(), object : NoteAdapter.onAdapterListener{
            override fun onClick(note: Note) {
                intentEdit(note.id, Constant.TYPE_READ)
            }
        })
         binding.rvNote.apply {
             layoutManager = LinearLayoutManager(applicationContext)
             adapter = noteAdapter

         }
     }
}