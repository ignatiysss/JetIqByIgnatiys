package com.example.jetiq.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.jetiq.R
import com.example.jetiq.databinding.FragmentBellsBinding

class BellsFragment : Fragment() {

    private lateinit var binding: FragmentBellsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBellsBinding.inflate(inflater, container, false)

        val bellsList = listOf(
            "08:15 - 09:00",
            "09:15 - 10:00",
            "10:15 - 11:00",
            "11:15 - 12:00",
            "12:15 - 13:00",
            "13:15 - 14:00",
            "14:15 - 15:00",
            "15:10 - 15:55",
            "16:05 - 16:50",
            "17:00 - 17:45",
            "17:55 - 18:40",
            "18:50 - 19:35",
            "19:45 - 20:25",
            "20:35 - 21:15"
        )

        binding.listViewBells.divider = null
        binding.listViewBells.adapter = BellsAdapter(
            requireContext(),
            R.layout.item_subject,
            bellsList
        )

        return binding.root
    }

    class BellsAdapter(
        context: Context,
        private val resource: Int,
        private val items: List<String>
    ) : ArrayAdapter<String>(context, resource, items) {

        private val inflater: LayoutInflater = LayoutInflater.from(context)

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view: View
            val viewHolder: ItemHolder

            if (convertView == null) {
                view = inflater.inflate(resource, parent, false)
                viewHolder = ItemHolder(view)
                view.tag = viewHolder
            } else {
                view = convertView
                viewHolder = view.tag as ItemHolder
            }

            viewHolder.tvSubjectName.text = items[position]
            (position + 1).toString().also { viewHolder.tvSubjectNumber.text = it }

            return view
        }

        private class ItemHolder(row: View) {
            val tvSubjectName: TextView = row.findViewById(R.id.tvSubjectName)
            val tvSubjectNumber: TextView = row.findViewById(R.id.tvSubjectNumber)
        }
    }
}
