package com.example.birthdaygreeting

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.birthdaygreeting.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var binding: ActivityMainBinding
    private var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        tts = TextToSpeech(this, this)

        binding.btnOk.setOnClickListener {
            if (binding.etName.text.isNullOrEmpty()) {
                //Toast.makeText(applicationContext, "Enter your name first", Toast.LENGTH_SHORT).show()
                binding.etName.error = "Enter your name first."
                return@setOnClickListener
            } else {
                val i = Intent(baseContext, BirthdayPage::class.java)
                val txtData = binding.etName.text.toString()
                i.putExtra("txtData", txtData)
                speakOut()
                startActivity(i)
            }
        }
    }

    private fun speakOut() {
        val text = binding.etName.text.toString()
        tts!!.speak("Happy Birthday $text", TextToSpeech.QUEUE_FLUSH, null, "")
    }

    public override fun onDestroy() {
        // Shutdown TTS
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            } else {
                binding.btnOk.isEnabled = true
            }
        } else {
            Log.e("TTS", "Initialization Failed!")
        }
    }
}