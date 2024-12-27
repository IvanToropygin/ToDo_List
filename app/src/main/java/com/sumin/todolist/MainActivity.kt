package com.sumin.todolist

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sumin.todolist.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var linearLayoutNotes: LinearLayout
    private lateinit var fabAddNote: FloatingActionButton
    private lateinit var notesList: MutableList<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initView()
        generateNotes()
        showNotes()
        fabAddNote.setOnClickListener {
            startActivity(AddNoteActivity.newIntent(this))
        }
    }

    private fun initView() {
        linearLayoutNotes = binding.linearLayoutNotes
        fabAddNote = binding.buttonAddNote
        notesList = mutableListOf()
    }

    private fun generateNotes() {
        for (i in 0 until 20) notesList.add(
            Note(
                id = i,
                noteContent = "Note $i",
                priority = Random.nextInt(1, 4)
            )
        )
    }

    private fun showNotes() {
        for (note in notesList) {
            val item: View = layoutInflater.inflate(R.layout.note_item, linearLayoutNotes, false)
            val textView = item.findViewById<TextView>(R.id.textViewNote)
            textView.text = note.noteContent
            val colorResId = when (note.priority) {
                1 -> android.R.color.holo_green_light
                2 -> android.R.color.holo_orange_light
                else -> android.R.color.holo_red_light
            }
            textView.setBackgroundColor(ContextCompat.getColor(this, colorResId))
            linearLayoutNotes.addView(item)
        }
    }
}