package com.infranetstudio.com.texttospeach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import com.infranetstudio.com.texttospeach.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var binding: ActivityMainBinding
    private var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tts = TextToSpeech(this, this)

        binding.btnSubmit.setOnClickListener {
            if(binding.etText.text.isEmpty()){
                Toast.makeText(this, "Please Enter Some Text", Toast.LENGTH_LONG ).show()
            }else{
                setText(binding.etText.text.toString())
            }
        }

    }

    override fun onInit(status: Int) {
       if(status == TextToSpeech.SUCCESS){
           val result = tts!!.setLanguage(Locale.ENGLISH)
       }
    }

    override fun onDestroy() {

        if(tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }

        super.onDestroy()
    }

    private fun setText(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
}