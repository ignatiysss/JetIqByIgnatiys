package com.example.jetiq.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.jetiq.R
import com.example.jetiq.databinding.FragmentScheduleBinding


class ScheduleFragment : Fragment() {
    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!

    private val subjects = listOf(
        "Математика",
        "Фізика",
        "Програмування",
        "Компютерно дискретна математика",
        "Вища математика",
        "Вища математика",
        "Вища математика",
        "Вища математка"
    )

    private fun showInfoDialog(subject: String) {
        val message = """
        Додаткова інформація про $subject
        Інформація про викладача
        Інформація про аудиторію
        Інформація про домашнє завдання
    """.trimIndent()

        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.custom_dialog, null)
        val titleView = dialogView.findViewById<TextView>(R.id.tvDialogTitle)
        val contentView = dialogView.findViewById<TextView>(R.id.tvDialogContent)

        titleView.text = "Інформація про предмет"
        contentView.text = message

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()

        dialog.setOnShowListener {
            dialog.window?.setBackgroundDrawable(
                ColorDrawable(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.dialogBackgroundColor
                    )
                )
            )
        }

        dialog.show()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listViewSubjects.divider = null
        binding.listViewSubjects.adapter =
            SubjectsAdapter(requireContext(), R.layout.item_subject, subjects)

        binding.listViewSubjects.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val subject = subjects[position]
                showInfoDialog(subject)
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class SubjectsAdapter(
    context: Context,
    private val resource: Int,
    private val items: List<String>
) : ArrayAdapter<String>(context, resource, items) {


    private val inflater: LayoutInflater = LayoutInflater.from(context)

    @SuppressLint("SetTextI18n")
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
        viewHolder.tvSubjectNumber.text = (position + 1).toString()


        return view
    }

    private class ItemHolder(row: View?) {
        val tvSubjectName: TextView = row?.findViewById(R.id.tvSubjectName) as TextView
        val tvSubjectNumber: TextView = row?.findViewById(R.id.tvSubjectNumber) as TextView

    }
}
