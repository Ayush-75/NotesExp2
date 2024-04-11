package com.example.notesexp2.fragments

import android.os.Bundle
import android.text.format.DateFormat.format
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.notesexp2.R
import com.example.notesexp2.databinding.FragmentAddNoteBinding
import com.example.notesexp2.model.Notes
import com.example.notesexp2.viewModel.NotesViewModel
import java.util.Date

class AddNoteFragment : Fragment() {

    lateinit var binding: FragmentAddNoteBinding
    var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)

        binding.pGreen.setImageResource(R.drawable.baseline_done)

        binding.saveNote.setOnClickListener {
            createNotes(it)
        }

        binding.pGreen.setOnClickListener {
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.baseline_done)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)

        }
        binding.pRed.setOnClickListener {
            priority = "2"
            binding.pRed.setImageResource(R.drawable.baseline_done)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }
        binding.pYellow.setOnClickListener {
            priority = "3"
            binding.pYellow.setImageResource(R.drawable.baseline_done)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }

        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.addTitle.text.toString()
        val SubTitle = binding.addSubTitle.text.toString()
        val notes = binding.addNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = format("MMMM d,yyyy", d.time)

        val data = Notes(title = title, subtitle = SubTitle, note = notes, date = notesDate.toString(),
            priority = priority
        )
        viewModel.addNotes(data)
        Toast.makeText(requireContext(), "notes created successfully", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_addNoteFragment_to_homeFragment)

    }

}