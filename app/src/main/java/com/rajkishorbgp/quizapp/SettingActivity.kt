package com.rajkishorbgp.quizapp

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.rajkishorbgp.quizapp.databinding.ActivitySettingBinding
import java.util.Locale

@Suppress("DEPRECATION")
class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadLocales()

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.changeLanguageCard.setOnClickListener {
            changeLanguage()
        }
    }

    private fun changeLanguage() {
        val language = arrayOf("English", "हिंदी", "اردو")
        val mBuilder = AlertDialog.Builder(this)
        mBuilder.setTitle("Choose Language")
        mBuilder.setSingleChoiceItems(language, -1) { dialog, which ->
            val languageCode = when (which) {
                0 -> "en"
                1 -> "hi"
                2 -> "ur"
                else -> "en"
            }
            setLocale(languageCode)
            dialog.dismiss()

            // Recreate MainActivity to apply language change
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        val dialog = mBuilder.create()
        dialog.show()
    }


    private fun setLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val configuration = Configuration()
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)

        val editor = getSharedPreferences("Settings", MODE_PRIVATE).edit()
        editor.putString("App_lang", language)
        editor.apply()
    }

    private fun loadLocales() {
        val preferences = getSharedPreferences("Settings", MODE_PRIVATE)
        val language = preferences.getString("App_lang", "")
        if (language != null) {
            setLocale(language)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}