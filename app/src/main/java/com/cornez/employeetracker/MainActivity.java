package com.cornez.employeetracker;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView name;
    private TextView task;
    private TextView timeDisplay;
    Button exitBtn;
//    private Button startBtn;
//    private Button stopBtn;
//    private Button resetBtn;

    private Timer watchTime;
    private long timeInMilliseconds;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_screen);

        setContentView(R.layout.activity_main);

        exitBtn = (Button) findViewById(R.id.mapExitButton);
        timeDisplay = (TextView) findViewById(R.id.mapTimer);
//        startBtn = (Button) findViewById(R.id.start_button);
//        stopBtn = (Button) findViewById(R.id.stop_button);
//        resetBtn = (Button) findViewById(R.id.reset_button);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        name = (TextView) findViewById(R.id.mapEmpNameTextView);
        task = (TextView) findViewById(R.id.mapTaskNameTextView);

//        stopBtn.setEnabled(false);
//        resetBtn.setEnabled(false);

        watchTime = new Timer();

        mHandler = new Handler();

        //Pipe Employee Information
        displayInfo();
    }

    public void changeView(View view) {
        View.OnClickListener handler = new View.OnClickListener() {
            public void onClick(View v) {
                if (v == exitBtn) {
                    Intent intentMain = new Intent(MainActivity.this,
                            LandingScreenActivity.class);
                    MainActivity.this.startActivity(intentMain);
                }
            }
        };

        exitBtn.setOnClickListener(handler);
    }

    public void startTimer(View view) {
//        stopBtn.setEnabled(true);
//        startBtn.setEnabled(false);
//        resetBtn.setEnabled(false);

        watchTime.setStartTime(SystemClock.uptimeMillis());
        mHandler.postDelayed(updateTimerRunnable, 20);
    }

    private Runnable updateTimerRunnable = new Runnable() {
        @Override
        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - watchTime.getStartTime();

            watchTime.setTimeUpdate(watchTime.getmStoredTime() + timeInMilliseconds);

            int time = (int) (watchTime.getTimeUpdate() / 1000);

            int minutes = time / 60;
            int seconds = time % 60;
            int milliseconds = (int) (watchTime.getTimeUpdate() % 1000);

            timeDisplay.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds) +
                    ":" + String.format("%02d", milliseconds));

            mHandler.postDelayed(this, 0);

        }
    };

    public void stopTimer(View view) {
//        stopBtn.setEnabled(false);
//        startBtn.setEnabled(true);
//        resetBtn.setEnabled(true);

        watchTime.setStartTime(SystemClock.uptimeMillis());

        mHandler.removeCallbacks(updateTimerRunnable);
    }

    public void resetTimer(View view) {
        watchTime.resetWatchTime();
        timeInMilliseconds = 0L;

        int minutes = 0;
        int seconds = 0;
        int milliseconds = 0;

        timeDisplay.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds) +
                ":" + String.format("%02d", milliseconds));
    }

    public void displayInfo()
    {
        if (LandingScreenActivity.employeeCole.getIsTracked())
        {
            name.setText(LandingScreenActivity.employeeCole.getName());
            if (LandingScreenActivity.employeeCole.getOnTask2())
            {
                task.setText(LandingScreenActivity.employeeCole.getTask2());
            }
            else
            {
                task.setText(LandingScreenActivity.employeeCole.getTask1());
            }
        }
        else if (LandingScreenActivity.employeeBob.getIsTracked())
        {
            name.setText(LandingScreenActivity.employeeBob.getName());
            if (LandingScreenActivity.employeeBob.getOnTask2())
            {
                task.setText(LandingScreenActivity.employeeBob.getTask2());
            }
            else
            {
                task.setText(LandingScreenActivity.employeeBob.getTask1());
            }
        }
        else
        {
            name.setText(LandingScreenActivity.employeeKyra.getName());
            if (LandingScreenActivity.employeeKyra.getOnTask2())
            {
                task.setText(LandingScreenActivity.employeeKyra.getTask2());
            }
            else
            {
                task.setText(LandingScreenActivity.employeeKyra.getTask1());
            }
        }
    }
}
