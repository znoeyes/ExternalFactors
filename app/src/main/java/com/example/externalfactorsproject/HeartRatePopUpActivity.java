// This activity displays camera output on the screen and detects a heartbeat from the blood
// oscillations present in the finger (placed on the camera lens) when the torch is enabled.  A
// bitmap is created from the texture (screen) in order to gather luminance information and
// detect a heart rate from the oscillations in the luminance values.

package com.example.externalfactorsproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.sql.Timestamp;

public class HeartRatePopUpActivity extends AppCompatActivity {
    //Heart rate detector member variables
    public static int hrtratebpm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure_heart_rate_popup);

        ConstraintLayout button_measure_heart_rate =  findViewById(R.id.button_measure_heart_rate);
        button_measure_heart_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
                startActivityForResult(intent,0);
            }
        });

        TextView button_pass = findViewById(R.id.button_pass);
        button_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(getApplicationContext(), "Result : " + resultCode, Toast.LENGTH_SHORT).show();
        //여기 활용
    }
}

class HeartRate {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    int heartrate = HeartRatePopUpActivity.hrtratebpm;

//    int
}










