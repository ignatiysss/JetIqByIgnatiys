package com.example.jetiq.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jetiq.databinding.FragmentHomeBinding
import com.example.jetiq.ui.message.MessageViewModel

class ScheduleFragment : Fragment() {

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

        binding.imageButtonMessageFragmentHome.setOnClickListener{

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}