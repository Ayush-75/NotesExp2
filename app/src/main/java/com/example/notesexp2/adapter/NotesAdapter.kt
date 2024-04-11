package com.example.notesexp2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notesexp2.R
import com.example.notesexp2.databinding.ItemNotesBinding
import com.example.notesexp2.fragments.HomeFragmentDirections
import com.example.notesexp2.model.Notes

class NotesAdapter(var context: Context,var notesList:List<Notes>):RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    inner class NotesViewHolder(var binding: ItemNotesBinding):RecyclerView.ViewHolder(binding.root)

    fun filtering(newFilteredList: ArrayList<Notes>) {
        notesList = newFilteredList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
               return NotesViewHolder( ItemNotesBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNote =notesList[position]
        with(holder){
            binding.reNotesTitle.text = currentNote.title
            binding.reNotesNote.text = currentNote.note
            binding.reDate.text = currentNote.date

            when(currentNote.priority){
                "1" -> {
                    binding.reViewPriority.setBackgroundResource(R.drawable.green_dot)
                }
                "2" -> {
                    binding.reViewPriority.setBackgroundResource(R.drawable.red_dot)
                }
                "3" -> {
                    binding.reViewPriority.setBackgroundResource(R.drawable.yellow_dot)
                }
            }

            binding.root.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(currentNote)
                Navigation.findNavController(it).navigate(action)
            }

            }

    }

    override fun getItemCount(): Int {
        return notesList.size
    }
}