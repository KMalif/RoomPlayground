 package com.example.roomplayground

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomplayground.adapter.NoteAdapter
import com.example.roomplayground.room.Note
import com.example.roomplayground.room.NoteDB
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

 class MainActivity : AppCompatActivity() {
     val db by lazy { NoteDB(this) }
     lateinit var noteAdapter: NoteAdapter


     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

    fun setupListener(){
        fab.setOnClickListener {
            startActivity(Intent(this, EditActivity::class.java))
        }
    }

     fun setupRecycler(){
        noteAdapter = NoteAdapter(arrayListOf(), object : NoteAdapter.onAdapterListener{
            override fun onClick(note: Note) {
                Toast.makeText(applicationContext, note.title, Toast.LENGTH_LONG).show()
            }
        })
         rvNote.apply {
             layoutManager = LinearLayoutManager(applicationContext)
             adapter = noteAdapter

         }
     }
}