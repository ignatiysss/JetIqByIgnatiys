package com.example.jetiq.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jetiq.MainActivity
import com.example.jetiq.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MessageFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_message, container, false)
    }


    override fun onResume() {
        super.onResume()
        // Hide the FAB
        (activity as? MainActivity)?.fab?.hide()
    }

    override fun onPause() {
        super.onPause()
        // Show the FAB
        (activity as? MainActivity)?.fab?.show()
    }


    override fun onDestroy() {
        super.onDestroy()
    }

}