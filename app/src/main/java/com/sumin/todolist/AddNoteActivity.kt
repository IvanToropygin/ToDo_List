package com.sumin.todolist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sumin.todolist.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    private lateinit var inputNoteContentField: EditText
    private lateinit var radioLow: RadioButton
    private lateinit var radioMedium: RadioButton
    private lateinit var radioHigh: RadioButton
    private lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        buttonSave.setOnClickListener {
            if (inputNoteContentField.text.toString().trim().isEmpty())
                Toast.makeText(
                    this@AddNoteActivity,
                    getString(R.string.note_content_is_required), Toast.LENGTH_SHORT
                )
                    .show()
            else {
                saveNote()
            }
        }
    }

    private fun initViews() {
        inputNoteContentField = binding.editTextNoteContent
        radioLow = binding.radioButtonLow
        radioMedium = binding.radioButtonMedium
        radioHigh = binding.radioButtonHigh
        buttonSave = binding.buttonSaveNote
    }

    private fun saveNote() {
        val inputText = inputNoteContentField.text.toString().trim()
        val priority = getPriority()

    }

    private fun getPriority() = when {
        (radioLow.isChecked) -> 1
        (radioMedium.isChecked) -> 2
        else -> 3
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, AddNoteActivity::class.java)
    }
}