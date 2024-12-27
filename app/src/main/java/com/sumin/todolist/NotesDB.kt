package com.sumin.todolist

import kotlin.random.Random

object NotesDB {
    private val notesList = mutableListOf<Note>()

    init {
        for (i in 0 until 5) {
            notesList.add(
                Note(
                    id = i,
                    noteContent = "Note $i",
                    priority = Random.nextInt(1, 4)
                )
            )
        }
    }

    fun addNote(note: Note) {
        notesList.add(note)
    }

    fun removeAt(id: Int) {
        notesList.removeIf { it.id == id }
    }

    fun getNotes(): List<Note> = notesList.toList()
}