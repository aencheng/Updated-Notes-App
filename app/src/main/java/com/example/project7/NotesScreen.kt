package com.example.project7

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.project7.ConfirmDeleteDialogFragment.Companion.TAG
import com.example.project7.databinding.FragmentNotesScreenBinding

class NotesScreen : Fragment() {
    private var _binding: FragmentNotesScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentNotesScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        val noteId = NotesScreenArgs.fromBundle(requireArguments()).noteId

        val viewModel : NotesViewModel by activityViewModels()
        viewModel.noteId = noteId
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        fun yesPressed(noteId : String) {
            Log.d(TAG, "in yesPressed(): noteId = $noteId")
            binding.viewModel?.deleteNote(noteId)
            findNavController().popBackStack()
        }

        // On click listener for delete button
        binding.deleteButton.setOnClickListener{
            ConfirmDeleteDialogFragment(noteId,::yesPressed).show(childFragmentManager,
                ConfirmDeleteDialogFragment.TAG)
        }

        viewModel.navigateToList.observe(viewLifecycleOwner, Observer { navigate ->
            if (navigate) {
                view.findNavController()
                    .navigate(R.id.action_notesScreen_to_mainScreen)
                viewModel.onNavigatedToList()
            }
        })
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}