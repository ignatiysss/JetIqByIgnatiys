package com.example.jetiq.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jetiq.MainActivity
import com.example.jetiq.R
import com.example.jetiq.databinding.FragmentHomeBinding
import com.example.jetiq.ui.message.MessageFragment
import com.example.jetiq.ui.message.MessageViewModel
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val messageViewModel =
            ViewModelProvider(this).get(MessageViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textViewNameStudent: TextView = binding.textNameStudent
        messageViewModel.text.observe(viewLifecycleOwner) {
            textViewNameStudent.text = it
        }



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        binding.smth.setOnClickListener {
            Toast.makeText(requireContext(), "smth", Toast.LENGTH_LONG).show()
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show() as MainActivity
        }

        binding.imageButtonMessageFragmentHome.setOnClickListener {

            Toast.makeText(context, "smth", Toast.LENGTH_LONG).show()

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}