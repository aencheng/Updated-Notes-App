package com.example.project7

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

class SplashFragment : Fragment() {

    val viewModel : NotesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onStart() {
        // determines if the user is logged in or not
        super.onStart()
        val currentUser = viewModel.getCurrentUser()

        val handler = Handler(Looper.myLooper()!!)
        handler.postDelayed({
            if (currentUser != null) {
                Log.i("CURRENT USER", currentUser.email.toString())
                this.findNavController().navigate(R.id.action_splashFragment_to_mainScreen)
            }
            else {
                this.findNavController().navigate(R.id.action_splashFragment_to_signInFragment)

            }

        }, 2000)
    }


}