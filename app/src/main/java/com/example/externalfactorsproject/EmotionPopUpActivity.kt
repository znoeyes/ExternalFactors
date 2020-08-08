package com.example.externalfactorsproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_choice_emotion_popup.*
import org.jetbrains.anko.startActivity

class EmotionPopUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice_emotion_popup)

        button_close.setOnClickListener {
            startActivity<HomeActivity>()
        }
    }
}