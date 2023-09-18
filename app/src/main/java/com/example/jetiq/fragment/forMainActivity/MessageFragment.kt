package com.example.jetiq.fragment.forMainActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetiq.MainActivity
import com.example.jetiq.R

class MessageFragment : Fragment() {



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


}