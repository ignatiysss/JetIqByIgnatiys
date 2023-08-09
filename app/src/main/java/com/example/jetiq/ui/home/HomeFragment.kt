package com.example.jetiq.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jetiq.R
import com.example.jetiq.databinding.FragmentHomeBinding
import com.example.jetiq.ui.message.MessageFragment
import com.example.jetiq.ui.message.MessageViewModel

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

        Toast.makeText(context, "smth", Toast.LENGTH_LONG).show()

        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        binding.smth.setOnClickListener {
            Toast.makeText(requireContext(), "smth", Toast.LENGTH_LONG).show()
        }

        binding.imageButtonMessageFragmentHome.setOnClickListener {

            Toast.makeText(context, "smth", Toast.LENGTH_LONG).show()
            val messageFragment = MessageFragment()
            fragmentTransaction.add(R.id.nav_view, messageFragment, "messageFragmentTag")
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}