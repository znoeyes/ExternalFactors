// This activity displays camera output on the screen and detects a heartbeat from the blood
// oscillations present in the finger (placed on the camera lens) when the torch is enabled.  A
// bitmap is created from the texture (screen) in order to gather luminance information and
// detect a heart rate from the oscillations in the luminance values.

package com.example.externalfactorsproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class HeartRatePopUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_measure_heart_rate_popup);

        //측정하기 버튼 클릭
        ConstraintLayout button_measure_heart_rate =  findViewById(R.id.button_measure_heart_rate);
        button_measure_heart_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
                startActivityForResult(intent,0);
                finish();
            }
        });

        //건너뛰기 버튼 클릭
        TextView button_pass = findViewById(R.id.button_pass);
        button_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //fork 시계반대방향 회전 애니메이션
        ImageView rotateFork = findViewById(R.id.imageView_fork);
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fork_animation);
        rotateFork.startAnimation(anim);
        //fork2 시계방향 회전 애니메이션
        ImageView rotateFork2 = findViewById(R.id.imageView_fork2);
        Animation anim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fork2_animation);
        rotateFork2.startAnimation(anim2);

        //지우기!! 임시테스트코드: 유튜브 비디오 화면 작동 확인
        ImageView imageView_fork = findViewById(R.id.imageView_fork);
        imageView_fork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlayMusicVideoActivity.class);
                startActivityForResult(intent,0);
                finish();
            }
        });
    }

    //바깥화면 클릭 시 닫히지 않게
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    //안드로이드 백버튼 막기
    public void onBackPressed(){
        return;
    }

    //심장박동 측정결과 토스트
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(getApplicationContext(), "Result : " + resultCode, Toast.LENGTH_SHORT).show();
        //여기 활용
    }

}

//class HeartRate {
//    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//    int heartrate = HeartRatePopUpActivity.hrtratebpm;
////    int
//}










