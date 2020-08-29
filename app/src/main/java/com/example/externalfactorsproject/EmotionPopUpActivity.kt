package com.example.externalfactorsproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_choice_emotion_popup.*
import org.jetbrains.anko.startActivity

class EmotionPopUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice_emotion_popup)

        //감정선택결과 DB로 연결시키기
        imageButton_happy.setOnClickListener {
            startActivity<HeartRatePopUpActivity>()
        }
        imageButton_flutter.setOnClickListener {
            startActivity<HeartRatePopUpActivity>()
        }
        imageButton_soso.setOnClickListener {
            startActivity<HeartRatePopUpActivity>()
        }
        imageButton_sad.setOnClickListener {
            startActivity<HeartRatePopUpActivity>()
        }
        imageButton_fun.setOnClickListener {
            startActivity<HeartRatePopUpActivity>()
        }
        imageButton_angry.setOnClickListener {
            startActivity<HeartRatePopUpActivity>()
        }

        //닫기 버튼 클릭
        button_close.setOnClickListener {
            startActivity<HomeActivity>()
        }
    }
}