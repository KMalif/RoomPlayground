package com.example.roomplayground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.roomplayground.databinding.ActivityEditBinding
import com.example.roomplayground.room.Constant
import com.example.roomplayground.room.Note
import com.example.roomplayground.room.NoteDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {

    private val db by lazy { NoteDB(this) }
    private lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupListener()
        needToGetData()
    }

    private fun setupView(){
        val intentType  = intent.getIntExtra("intent_type", 0)
        when (intentType){
            Constant.TYPE_READ -> {
                binding.saveButton.visibility = View.GONE
            }
            Constant.TYPE_CREATE -> {

            }
        }
    }
    private fun setupListener(){
        binding.saveButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.noteDao().addNote(
                    Note(0, binding.titleEditText.text.toString(), binding.noteEditText.text.toString())
                )
                finish()
            }
        }
    }

    private fun needToGetData(){

    }
}