package com.example.externalfactorsproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_good_or_bad_popup.*
import org.jetbrains.anko.startActivity

class GoodOrBadPopupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_good_or_bad_popup)

        //노래추천 평가결과 DB로 연결시키기
        imageButton_good.setOnClickListener {
            finish()
        }
        imageButton_bad.setOnClickListener {
            finish()
        }
    }
}