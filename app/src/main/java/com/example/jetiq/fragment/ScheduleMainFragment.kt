import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetiq.R
import com.example.jetiq.databinding.FragmentScheduelMainBinding
import com.example.jetiq.fragment.BellsFragment
import com.example.jetiq.fragment.LessonsFragment
import com.example.jetiq.fragment.SessionsFragment

class ScheduleMainFragment() : Fragment() {
    private lateinit var _binding: FragmentScheduelMainBinding
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScheduelMainBinding.inflate(inflater, container, false)

        binding.bottomNavigationViewForScheduleMain.setOnNavigationItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.scheduleOfLessons -> LessonsFragment()
                R.id.scheduleOfBells -> BellsFragment()
                R.id.scheduleOfSessions -> SessionsFragment()
                else -> LessonsFragment()
            }
//
//            childFragmentManager.beginTransaction()
//                .replace(R.id.fragmentContainerForScheduleMain, fragment)
//                .commit()
            true
        }

        return binding.root
    }
}
