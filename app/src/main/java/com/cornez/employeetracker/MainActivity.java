package com.cornez.employeetracker;

import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView name;
    private TextView task;
    private TextView timeDisplay;
    private Button exitBtn;
    private Button startBtn;
    private Button stopBtn;
    private Button resetBtn;

    private Timer watchTime;
    private long timeInMilliseconds;

    private Handler mHandler;

    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exitBtn = (Button) findViewById(R.id.mapExitButton);
        timeDisplay = (TextView) findViewById(R.id.mapTimer);
        startBtn = (Button) findViewById(R.id.startButton);
        stopBtn = (Button) findViewById(R.id.stopButton);
        resetBtn = (Button) findViewById(R.id.resetButton);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        name = (TextView) findViewById(R.id.mapEmpNameTextView);
        task = (TextView) findViewById(R.id.mapTaskNameTextView);

        startBtn.setEnabled(true);
        stopBtn.setEnabled(false);
        resetBtn.setEnabled(false);

        if (Employee.firstTimer)
        {
            watchTime = new Timer();

            mHandler = new Handler();

            Employee.firstTimer = false;
        }
        else
        {
            if (LandingScreenActivity.employeeCole.getIsTracked())
            {
                timeDisplay.setText(LandingScreenActivity.employeeCole.get_time());
            }
            else if (LandingScreenActivity.employeeBob.getIsTracked())
            {
                timeDisplay.setText(LandingScreenActivity.employeeBob.get_time());
            }
            else
            {
                timeDisplay.setText(LandingScreenActivity.employeeKyra.get_time());
            }
        }

        //Pipe Employee Information
        displayInfo();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        location();
    }

    public void changeView(final View view) {
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
        stopBtn.setEnabled(true);
        startBtn.setEnabled(false);
        resetBtn.setEnabled(false);

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
            int milliseconds = (int) (watchTime.getTimeUpdate() % 100);

            String min = String.format(Locale.US, "%02d", minutes);
            String sec = String.format(Locale.US, "%02d", seconds);
            String mili = String.format(Locale.US, "%02d", milliseconds);


            String displayTime = min + ":" + sec + ": " + mili;

            timeDisplay.setText(displayTime);

            mHandler.postDelayed(this, 0);

        }
    };

    public void stopTimer(View view) {
        stopBtn.setEnabled(false);
        startBtn.setEnabled(true);
        resetBtn.setEnabled(true);

        watchTime.addStoredTime(timeInMilliseconds);

        mHandler.removeCallbacks(updateTimerRunnable);
    }

    public void resetTimer(View view) {
        if (LandingScreenActivity.employeeCole.getIsTracked())
        {
            LandingScreenActivity.employeeCole.setTime(timeDisplay.getText().toString());
        }
        else if (LandingScreenActivity.employeeBob.getIsTracked())
        {
            LandingScreenActivity.employeeBob.setTime(timeDisplay.getText().toString());
        }
        else
        {
            LandingScreenActivity.employeeKyra.setTime(timeDisplay.getText().toString());
        }

        watchTime.resetWatchTime();
        timeInMilliseconds = 0L;

        int minutes = 0;
        int seconds = 0;
        int milliseconds = 0;

        String min = String.format(Locale.US, "%02d", minutes);
        String sec = String.format(Locale.US, "%02d", seconds);
        String mili = String.format(Locale.US, "%02d", milliseconds);

        String displayTime = min + ":" + sec + ": " + mili;

        timeDisplay.setText(displayTime);

        Employee.firstTimer = true;
    }

    public void showTime(View view)
    {
        if (LandingScreenActivity.employeeCole.getIsTracked())
        {
            timeDisplay.setText(LandingScreenActivity.employeeCole.get_time());
        }
        else if (LandingScreenActivity.employeeBob.getIsTracked())
        {
            timeDisplay.setText(LandingScreenActivity.employeeBob.get_time());
        }
        else
        {
            timeDisplay.setText(LandingScreenActivity.employeeKyra.get_time());
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void location()
    {
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {

                }
            }
        })
    }
}
