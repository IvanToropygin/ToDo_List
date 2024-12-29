package com.sumin.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sumin.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerViewNotes: RecyclerView
    private lateinit var fabAddNote: FloatingActionButton

    private val notesAdapter: NotesAdapter by lazy { NotesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        binding.recyclerViewNotes.adapter = notesAdapter
        showNotes()

        notesAdapter.setOnNoteClickListener(object : NotesAdapter.OnNoteClickListener {
            override fun onNoteClick(note: Note) {
                notesAdapter.removeNote(note)
                NotesDB.removeAt(note.id)
            }
        })

        fabAddNote.setOnClickListener {
            startActivity(AddNoteActivity.newIntent(this))
        }
    }

    override fun onResume() {
        super.onResume()
        showNotes()
    }

    private fun showNotes() {
        notesAdapter.setNotes(NotesDB.getNotes())
    }

    private fun initView() {
        recyclerViewNotes = binding.recyclerViewNotes
        fabAddNote = binding.buttonAddNote
    }
}