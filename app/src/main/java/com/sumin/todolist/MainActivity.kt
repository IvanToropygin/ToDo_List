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

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var linearLayoutNotes: LinearLayout
    private lateinit var fabAddNote: FloatingActionButton

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
        fabAddNote.setOnClickListener {
            startActivity(AddNoteActivity.newIntent(this))
        }
    }

    override fun onResume() {
        super.onResume()
        showNotes()
    }

    private fun initView() {
        linearLayoutNotes = binding.linearLayoutNotes
        fabAddNote = binding.buttonAddNote
    }

    private fun showNotes() {
        linearLayoutNotes.removeAllViews()
        val noteList = NotesDB.getNotes()
        if (noteList.isEmpty()) {
            return
        }

        noteList.forEach { note ->
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

            textView.setOnClickListener {
                NotesDB.removeAt(note.id)
                showNotes()
            }
        }
    }
}