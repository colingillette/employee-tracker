package com.cornez.employeetracker;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class LandingScreenActivity extends AppCompatActivity
{
    public static Employee employeeCole;
    public static Employee employeeBob;
    public static Employee employeeKyra;

    Button btnEmployer;
    Button btnEmployee;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_screen);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        btnEmployer = (Button) findViewById(R.id.landingEmployerButton);
        btnEmployee = (Button) findViewById(R.id.landingEmployeeButton);

        firstTime();
    }

    // Transitions between layouts
    public void changeLayout(View view)
    {
        View.OnClickListener handler = new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (v == btnEmployer)
                {
                    Intent intentMain = new Intent(LandingScreenActivity.this,
                            EmployerViewActivity.class);
                    LandingScreenActivity.this.startActivity(intentMain);

                }
                if (v == btnEmployee)
                {
                    Intent intentMain = new Intent(LandingScreenActivity.this,
                            EmployeeViewActivity.class);
                    LandingScreenActivity.this.startActivity(intentMain);
                }
            }
        };

        btnEmployee.setOnClickListener(handler);
        btnEmployer.setOnClickListener(handler);
    }

    public void firstTime()
    {
        if (Employee.firstInstance)
        {
            employeeCole = new Employee(1,"Cole Jacobs", "Clean Glass Hall", "Pickup Boss' Laundry");
            employeeBob = new Employee(2, "Bob Lindsay", "Hammer a nail", "Drill a screw");
            employeeKyra = new Employee(3, "Kyra Mill", "Cook for a banquet", "Clean the banquet");

            Employee.firstInstance = false;
        }
    }
}