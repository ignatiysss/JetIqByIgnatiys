package com.example.jetiq.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetiq.MainActivity
import com.example.jetiq.R
import com.example.jetiq.databinding.FragmentScheduelMainBinding

@Suppress("DEPRECATION")
class ScheduleMainFragment : Fragment() {
    private lateinit var _binding: FragmentScheduelMainBinding
    private val binding get() = _binding

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScheduelMainBinding.inflate(inflater, container, false)

        binding.bottomNavigationViewForScheduleMain.selectedItemId = R.id.scheduleOfLessons
        childFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerForScheduleMain, LessonsFragment())
            .commit()

        binding.bottomNavigationViewForScheduleMain.setOnNavigationItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.scheduleOfLessons -> LessonsFragment()
                R.id.scheduleOfBells -> BellsFragment()
                R.id.scheduleOfSessions -> SessionsFragment()
                else -> LessonsFragment()
            }

            childFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerForScheduleMain, fragment)
                .commit()
            true
        }

            (requireActivity() as MainActivity).supportActionBar?.title = "Заголовок:\n Підтекст"

        return binding.root
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
