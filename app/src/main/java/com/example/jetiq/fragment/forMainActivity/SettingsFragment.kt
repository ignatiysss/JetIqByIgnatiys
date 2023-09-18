package com.example.jetiq.fragment.forMainActivity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import com.example.jetiq.MainActivity
import com.example.jetiq.R

@Suppress("DEPRECATION")
class SettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == "theme_preference") {
            val themeOption = sharedPreferences?.getString(key, "light")
            applyTheme(themeOption)
        }
    }

    private fun saveThemeState(themeOption: String?) {
        val sharedPreferences =
            requireActivity().getSharedPreferences("app_settings", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("theme_preference", themeOption)
        editor.apply()
    }


    private fun applyTheme(themeOption: String?) {
        val mainActivity = activity as? MainActivity
        val toggleDrawable = mainActivity?.toggle
        saveThemeState(themeOption)

        when (themeOption) {
            "light" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                toggleDrawable?.drawerArrowDrawable?.color = resources.getColor(R.color.colorText)
                mainActivity?.recreate()

            }

            "dark" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                toggleDrawable?.drawerArrowDrawable?.color = resources.getColor(R.color.colorTextNight)
                mainActivity?.recreate()


            }
        }
    }
}