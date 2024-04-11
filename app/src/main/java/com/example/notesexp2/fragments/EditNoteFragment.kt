package com.example.notesexp2.fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesexp2.R
import com.example.notesexp2.databinding.DialogDeleteBinding
import com.example.notesexp2.databinding.FragmentEditNoteBinding
import com.example.notesexp2.model.Notes
import com.example.notesexp2.viewModel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Date


class EditNoteFragment : Fragment() {

    val Editnotes by navArgs<EditNoteFragmentArgs>()
    private lateinit var binding:FragmentEditNoteBinding
    var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditNoteBinding.inflate(layoutInflater,container,false)

        binding.editTitle.setText(Editnotes.data.title)
        binding.editSubtitle.setText(Editnotes.data.subtitle)
        binding.editNotes.setText(Editnotes.data.note)

        setHasOptionsMenu(true)

        when(Editnotes.data.priority){
            "1" -> {
                priority = "1"
                binding.ePGreen.setImageResource(R.drawable.baseline_done)
                binding.ePRed.setImageResource(0)
                binding.ePYellow.setImageResource(0)

            }
            "2" -> {
                priority = "2"
                binding.ePRed.setImageResource(R.drawable.baseline_done)
                binding.ePGreen.setImageResource(0)
                binding.ePYellow.setImageResource(0)
            }
            "3" -> {
                priority = "3"
                binding.ePYellow.setImageResource(R.drawable.baseline_done)
                binding.ePRed.setImageResource(0)
                binding.ePGreen.setImageResource(0)
            }
        }

        binding.ePGreen.setOnClickListener {
            priority = "1"
            binding.ePGreen.setImageResource(R.drawable.baseline_done)
            binding.ePRed.setImageResource(0)
            binding.ePYellow.setImageResource(0)

        }
        binding.ePRed.setOnClickListener {
            priority = "2"
            binding.ePRed.setImageResource(R.drawable.baseline_done)
            binding.ePGreen.setImageResource(0)
            binding.ePYellow.setImageResource(0)
        }
        binding.ePYellow.setOnClickListener {
            priority = "3"
            binding.ePYellow.setImageResource(R.drawable.baseline_done)
            binding.ePRed.setImageResource(0)
            binding.ePGreen.setImageResource(0)
        }

        binding.editSaveNote.setOnClickListener {
            updateNotes(it)
        }


        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.editTitle.text.toString()
        val SubTitle = binding.editSubtitle.text.toString()
        val notes = binding.editNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d,yyyy", d.time)

        val data = Notes(Editnotes.data.id,title = title, subtitle = SubTitle, note = notes, date = notesDate.toString(),
            priority = priority
        )
        viewModel.updateNotes(data)
        Toast.makeText(requireContext(), "notes Updated successfully", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_editNoteFragment_to_homeFragment)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_list_delete){

            val bottomSheet:BottomSheetDialog = BottomSheetDialog(requireContext())
            val binding = DialogDeleteBinding.inflate(layoutInflater)
            bottomSheet.setContentView(binding.root)

            binding.dialogYes.setOnClickListener {

                viewModel.deleteNotes(Editnotes.data.id!!)
                bottomSheet.dismiss()
//                Navigation.findNavController(it!!).navigate(R.id.action_editNoteFragment_to_homeFragment)
                findNavController().popBackStack()
            }
            binding.dialogNo.setOnClickListener {
                bottomSheet.dismiss()
            }

            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }
}