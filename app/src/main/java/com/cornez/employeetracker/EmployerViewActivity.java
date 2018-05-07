package com.cornez.employeetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class EmployerViewActivity extends AppCompatActivity
{
    public static String taggedEmployee;

    Button btnView;
    Button btnExit;
    Button btnTask;
    RadioButton firstEmp, secondEmp, thirdEmp;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.employer_view);

        btnView = (Button) findViewById(R.id.employerViewButton);
        btnExit= (Button) findViewById(R.id.employerViewExitButton);
        btnTask = (Button) findViewById(R.id.employerViewTaskButton);

        firstEmp = (RadioButton) findViewById(R.id.employee1);
        secondEmp = (RadioButton) findViewById(R.id.employee2);
        thirdEmp = (RadioButton) findViewById(R.id.employee3);

        clearTrackers();
        displayEmployees();
    }

    //Transition between layouts
    public void changeLayout(View view)
    {
        View.OnClickListener handler = new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (v == btnView)
                {
                    trackEmployee();

                    Intent intentMain = new Intent(EmployerViewActivity.this,
                            MainActivity.class);
                    EmployerViewActivity.this.startActivity(intentMain);
                }
                if (v == btnExit)
                {
                    Intent intentMain = new Intent(EmployerViewActivity.this,
                            LandingScreenActivity.class);
                    EmployerViewActivity.this.startActivity(intentMain);
                }
                if (v == btnTask)
                {
                    trackEmployee();

                    Intent intentMain = new Intent(EmployerViewActivity.this,
                            EmployerTaskActivity.class);
                    EmployerViewActivity.this.startActivity(intentMain);
                }
            }
        };

        btnView.setOnClickListener(handler);
        btnExit.setOnClickListener(handler);
        btnTask.setOnClickListener(handler);
    }

    public void displayEmployees()
    {
        firstEmp.setText(LandingScreenActivity.employeeCole.getName());
        secondEmp.setText(LandingScreenActivity.employeeBob.getName());
        thirdEmp.setText(LandingScreenActivity.employeeKyra.getName());
    }

    public void trackEmployee()
    {
        if (firstEmp.isChecked())
        {
            LandingScreenActivity.employeeCole.setIsTracked(true);
        }
        else if (secondEmp.isChecked())
        {
            LandingScreenActivity.employeeBob.setIsTracked(true);
        }
        else
        {
            LandingScreenActivity.employeeKyra.setIsTracked(true);
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
