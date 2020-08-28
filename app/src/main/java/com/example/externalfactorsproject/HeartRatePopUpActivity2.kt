package com.example.externalfactorsproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_measure_heart_rate_popup.*
import org.jetbrains.anko.startActivity

class HeartRatePopUpActivity2 : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_measure_heart_rate_popup)

        button_pass.setOnClickListener {
            startActivity<HomeActivity>()
        }

        button_measure_heart_rate.setOnClickListener {
//            startActivity<>() // 심장 박동 기능 연결
        }
    }
}