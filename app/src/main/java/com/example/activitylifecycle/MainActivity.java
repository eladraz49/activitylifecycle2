package com.example.activitylifecycle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private long startTime = 0;
    private long totalTime = 0;
    private TextView timeTextView;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeTextView = findViewById(R.id.timeTextView);
        resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        Log.d("ActivityLifecycle", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ActivityLifecycle", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTime = System.currentTimeMillis();
        Log.d("ActivityLifecycle", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        long elapsedTime = System.currentTimeMillis() - startTime;
        totalTime += elapsedTime / 1000;
        Log.d("ActivityLifecycle", "onPause. Time visible: " + elapsedTime / 1000 + " seconds.");
        updateTimeDisplay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        long elapsedTime = System.currentTimeMillis() - startTime;
        totalTime += elapsedTime / 1000;
        Log.d("ActivityLifecycle", "onStop. Total time visible: " + totalTime + " seconds.");
        updateTimeDisplay();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ActivityLifecycle", "onDestroy");
    }

    private void updateTimeDisplay() {
        timeTextView.setText("Total time: " + totalTime + " seconds");
    }

    private void resetTimer() {
        totalTime = 0;
        updateTimeDisplay();
        Log.d("ActivityLifecycle", "Timer reset");
    }
}







