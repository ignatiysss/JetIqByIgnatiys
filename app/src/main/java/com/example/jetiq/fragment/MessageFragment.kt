package com.example.jetiq.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
        // Change the FAB properties for the MessageFragment
        (activity as? MainActivity)?.fab?.apply {
            setImageResource(R.drawable.baseline_edit_24) // Change the icon

        }
    }

    override fun onPause() {
        super.onPause()
        // Reset the FAB properties to the default
        (activity as? MainActivity)?.fab?.apply {
            setImageResource(R.drawable.baseline_message_24) // Reset the icon

        }
    }



    override fun onDestroy() {
        super.onDestroy()
    }

}