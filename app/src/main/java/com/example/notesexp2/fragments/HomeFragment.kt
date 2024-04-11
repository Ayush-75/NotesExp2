package com.example.notesexp2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesexp2.R
import com.example.notesexp2.adapter.NotesAdapter
import com.example.notesexp2.databinding.FragmentHomeBinding
import com.example.notesexp2.model.Notes
import com.example.notesexp2.viewModel.NotesViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()
    lateinit var adapter: NotesAdapter
    var oldMyNotes = arrayListOf<Notes>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)



        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        // adapter
        adapter = NotesAdapter(requireContext(), emptyList())
        binding.recyclerView.adapter = adapter

        //layout assign
        val layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        binding.recyclerView.layoutManager = layoutManager


//        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
//        binding.recyclerView.setHasFixedSize(true)
//        staggeredGridLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS



        viewModel.getALlNotes().observe(viewLifecycleOwner) { notesList ->

//            binding.recyclerView.layoutManager =StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
//            adapter = NotesAdapter(requireContext(), notesList)
//            binding.recyclerView.adapter = adapter
            oldMyNotes = notesList as ArrayList<Notes>
            adapter = NotesAdapter(requireContext(),notesList)
            binding.recyclerView.adapter = adapter
        }

        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_addNoteFragment)

        }

        binding.allNotes.setOnClickListener {
            viewModel.getALlNotes().observe(viewLifecycleOwner) { notesList ->
                binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), notesList)
                binding.recyclerView.adapter = adapter
            }
        }

            binding.filterLow.setOnClickListener {
                viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                    binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                    oldMyNotes = notesList as ArrayList<Notes>
                    adapter = NotesAdapter(requireContext(), notesList)
                    binding.recyclerView.adapter = adapter
                }
            }
            binding.filterMedium.setOnClickListener {
                viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->


                    binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                    oldMyNotes = notesList as ArrayList<Notes>
                    adapter = NotesAdapter(requireContext(), notesList)
                    binding.recyclerView.adapter = adapter
                }
            }
            binding.filterHigh.setOnClickListener {
                viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->


                    binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                    oldMyNotes = notesList as ArrayList<Notes>
                    adapter = NotesAdapter(requireContext(), notesList)
                    binding.recyclerView.adapter = adapter
                }
            }
            return binding.root
        }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as androidx.appcompat.widget.SearchView
        searchView.queryHint = " Enter Notes here..."
        searchView.setOnQueryTextListener(object :androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                notesFiltering(newText)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun notesFiltering(newText: String?) {
        val newFilteredList = arrayListOf<Notes>()
        for (i in oldMyNotes){
            if (i.title.contains(newText!!) ||i.note.contains(newText!!)){
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)
    }
}
