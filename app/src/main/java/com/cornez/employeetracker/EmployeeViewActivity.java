package com.cornez.employeetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EmployeeViewActivity extends AppCompatActivity
{
    EditText checkInLine;
    String employee;

    Button btnIn;
    Button btnOut;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.employee_view);
        btnIn = (Button) findViewById(R.id.empViewInButton);
        btnOut = (Button) findViewById(R.id.empViewOutButton);
        checkInLine = (EditText) findViewById(R.id.empViewEditText);

        clearTrackers();
    }

    //Transition between layouts
    public void changeLayout(View view)
    {
        View.OnClickListener handler = new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (v == btnIn)
                {
                    trackEmployee();

                    Intent intentMain = new Intent(EmployeeViewActivity.this,
                            EmployeeTaskActivity.class);
                    EmployeeViewActivity.this.startActivity(intentMain);
                }
                if (v == btnOut)
                {
                    LandingScreenActivity.employeeCole.disableTracking();
                    LandingScreenActivity.employeeBob.disableTracking();
                    LandingScreenActivity.employeeKyra.disableTracking();

                    Intent intentMain = new Intent(EmployeeViewActivity.this,
                            LandingScreenActivity.class);
                    EmployeeViewActivity.this.startActivity(intentMain);
                }
            }
        };

        btnIn.setOnClickListener(handler);
        btnOut.setOnClickListener(handler);
    }

    public void trackEmployee()
    {
        employee = checkInLine.getText().toString();
        switch (employee) {
            case "Cole":
                LandingScreenActivity.employeeCole.setIsTracked(true);
                break;
            case "Bob":
                LandingScreenActivity.employeeBob.setIsTracked(true);
                break;
            case "Kyra":
                LandingScreenActivity.employeeKyra.setIsTracked(true);
                break;
        }
    }

    public void clearTrackers()
    {
        LandingScreenActivity.employeeCole.setIsTracked(false);
        LandingScreenActivity.employeeBob.setIsTracked(false);
        LandingScreenActivity.employeeKyra.setIsTracked(false);

        LandingScreenActivity.employeeCole.setOnTask1(false);
        LandingScreenActivity.employeeCole.setOnTask2(false);

        LandingScreenActivity.employeeBob.setOnTask1(false);
        LandingScreenActivity.employeeBob.setOnTask2(false);

        LandingScreenActivity.employeeKyra.setOnTask1(false);
        LandingScreenActivity.employeeKyra.setOnTask2(false);
    }
}
