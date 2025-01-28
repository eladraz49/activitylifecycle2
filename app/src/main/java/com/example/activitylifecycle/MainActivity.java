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

        // קבלת הפניות לרכיבי ה-UI
        timeTextView = findViewById(R.id.timeTextView);
        resetButton = findViewById(R.id.resetButton);

        // קביעת OnClickListener לכפתור איפוס
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer(); // איפוס זמן
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
        startTime = System.currentTimeMillis();  // זמן התחלה של הצגת האפליקציה
        Log.d("ActivityLifecycle", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        long elapsedTime = System.currentTimeMillis() - startTime;
        totalTime += elapsedTime / 1000;  // המרת מילישניות לשניות
        Log.d("ActivityLifecycle", "onPause. Time visible: " + elapsedTime / 1000 + " seconds.");
        updateTimeDisplay();  // עדכון תצוגה
    }

    @Override
    protected void onStop() {
        super.onStop();
        long elapsedTime = System.currentTimeMillis() - startTime;
        totalTime += elapsedTime / 1000;
        Log.d("ActivityLifecycle", "onStop. Total time visible: " + totalTime + " seconds.");
        updateTimeDisplay();  // עדכון תצוגה
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ActivityLifecycle", "onDestroy");
    }

    private void updateTimeDisplay() {
        // עדכון טקסט ב-TextView
        timeTextView.setText("Total time: " + totalTime + " seconds");
    }

    private void resetTimer() {
        // איפוס הזמן הכולל
        totalTime = 0;
        updateTimeDisplay();  // עדכון תצוגה
        Log.d("ActivityLifecycle", "Timer reset");
    }
}







