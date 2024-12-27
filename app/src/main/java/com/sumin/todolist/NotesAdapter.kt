package com.sumin.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sumin.todolist.databinding.NoteItemBinding

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var notes = listOf<Note>()

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    class NotesViewHolder(private val binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Note) {
            binding.textViewNote.text = item.noteContent
            val colorResId = when (item.priority) {
                1 -> android.R.color.holo_green_light
                2 -> android.R.color.holo_orange_light
                else -> android.R.color.holo_red_light
            }
            binding.textViewNote.setBackgroundColor(
                ContextCompat.getColor(
                    binding.root.context,
                    colorResId
                )
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: NotesViewHolder, position: Int) {
        viewHolder.bind(notes[position])
    }

    override fun getItemCount() = notes.size
}