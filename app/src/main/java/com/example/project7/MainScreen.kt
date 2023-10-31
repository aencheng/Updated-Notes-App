package com.example.project7

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.project7.databinding.FragmentMainScreenBinding

class MainScreen : Fragment()   {
    val TAG = "MainScreen"
    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        val viewModel : NotesViewModel by activityViewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        fun noteClicked (note : Note) {
            viewModel.onNoteClicked(note)
        }

        val adapter = NoteItemAdapter(::noteClicked)

        binding.notesList.adapter = adapter

        // staggered layout manager
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.notesList.layoutManager = layoutManager

        // observer for note updates
        viewModel.notes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToNote.observe(viewLifecycleOwner, Observer { noteId ->
            noteId?.let {
                val action = MainScreenDirections
                    .actionMainScreenToNotesScreen(noteId)
                this.findNavController().navigate(action)
                viewModel.onNoteNavigated()
            }
        })
        viewModel.navigateToSignIn.observe(viewLifecycleOwner, Observer { navigate ->
            if(navigate) {
                this.findNavController().navigate(R.id.action_mainScreen_to_signInFragment)
                viewModel.onNavigatedToSignIn()
            }
        })

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}