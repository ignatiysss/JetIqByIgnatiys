package com.example.jetiq.fragment.forMainActivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jetiq.MainActivity
import com.example.jetiq.R

class WriteMessageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_write_message, container, false)
    }

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.fab?.apply {
            visibility = View.GONE
        }
    }

    override fun onPause() {
        super.onPause()
        (activity as? MainActivity)?.fab?.apply {
            visibility = View.VISIBLE
        }

    }
}