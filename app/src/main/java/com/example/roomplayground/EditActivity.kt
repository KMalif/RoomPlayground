package com.example.roomplayground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roomplayground.room.Note
import com.example.roomplayground.room.NoteDB
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {

    val db by lazy { NoteDB(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setupListener()
    }


    fun setupListener(){
        save_button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.noteDao().addNote(
                    Note(0, title_editText.text.toString(), note_editText.text.toString())
                )
                finish()
            }
        }
    }
}