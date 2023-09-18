package com.example.jetiq.fragment.forMainActivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.example.jetiq.MainActivity
import com.example.jetiq.R
import com.example.jetiq.databinding.FragmentHomeBinding
import com.example.jetiq.fragment.ScheduleFragment


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    //TODO make scrolling
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            (activity as MainActivity).finish()
        }

        (requireActivity() as MainActivity).supportActionBar?.title = "JetIq"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val scheduleFragment = ScheduleFragment()
        childFragmentManager.beginTransaction()
            .add(R.id.childFragmentContainer, scheduleFragment)
            .commit()
    }
}