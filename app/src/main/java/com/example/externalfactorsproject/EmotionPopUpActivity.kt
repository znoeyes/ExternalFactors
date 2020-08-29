package com.example.externalfactorsproject

import android.os.Bundle
import android.view.MotionEvent
import android.view.Window
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_choice_emotion_popup.*
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.startActivity

class EmotionPopUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice_emotion_popup)
        this.setFinishOnTouchOutside(false) //팝업 바깥 화면 클릭 시 팝업 닫히지 않게
        animateFork1() //fork 시계반대방향 회전 애니메이션
        animateFork2() //fork2 시계방향 회전 애니메이션

        //감정선택결과 DB로 연결시키기
        imageButton_happy.setOnClickListener {
            startActivity<HeartRatePopUpActivity>()
            finish()
        }
        imageButton_flutter.setOnClickListener {
            startActivity<HeartRatePopUpActivity>()
            finish()
        }
        imageButton_soso.setOnClickListener {
            startActivity<HeartRatePopUpActivity>()
            finish()
        }
        imageButton_sad.setOnClickListener {
            startActivity<HeartRatePopUpActivity>()
            finish()
        }
        imageButton_fun.setOnClickListener {
            startActivity<HeartRatePopUpActivity>()
            finish()
        }
        imageButton_angry.setOnClickListener {
            startActivity<HeartRatePopUpActivity>()
            finish()
        }

        //닫기 버튼 클릭
        button_close.setOnClickListener {
            finish()
        }

    }

    //fork 시계반대방향 회전 애니메이션
    private fun animateFork1(){
        val rotateFork = AnimationUtils.loadAnimation(this, R.anim.fork_animation)
        imageView_fork.animation = rotateFork
    }
    //fork2 시계방향 회전 애니메이션
    private fun animateFork2(){
        val rotateFork2 = AnimationUtils.loadAnimation(this, R.anim.fork2_animation)
        imageView_fork2.animation = rotateFork2
    }
}