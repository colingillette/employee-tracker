package com.cornez.employeetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

public class EmployeeTaskActivity extends AppCompatActivity
{
    Button btnView;
    Button btnClear;
    Button btnBack;

    RadioButton select1;
    RadioButton select2;

    RelativeLayout employeeTask;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_task_view);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        btnView = (Button) findViewById(R.id.taskViewButton);
        btnClear= (Button) findViewById(R.id.taskClearButton);
        btnBack = (Button) findViewById(R.id.taskViewBackButton);
        select1 = (RadioButton) findViewById(R.id.firstRB);
        select2 = (RadioButton) findViewById(R.id.secondRB);
        employeeTask = (RelativeLayout) findViewById(R.id.employeeTaskViewLayout);

        showTasks();
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
                    trackTask();

                    Intent intentMain = new Intent(EmployeeTaskActivity.this,
                            MainActivity.class);
                    EmployeeTaskActivity.this.startActivity(intentMain);
                }
                if (v == btnBack)
                {
                    Intent intentMain = new Intent(EmployeeTaskActivity.this,
                            EmployeeViewActivity.class);
                    EmployeeTaskActivity.this.startActivity(intentMain);
                }
                if (v == btnClear)
                {
                    clearSelection();
                }
            }
        };

        btnView.setOnClickListener(handler);
        btnBack.setOnClickListener(handler);
        btnClear.setOnClickListener(handler);
    }

    public void clearSelection()
    {
        if (select1.isChecked() || select2.isChecked())
        {
            if(select1.isChecked())
            {
                if (LandingScreenActivity.employeeCole.getIsTracked())
                {
                    select1.setText(LandingScreenActivity.employeeCole.completeTask(0));
                }
                else if (LandingScreenActivity.employeeBob.getIsTracked())
                {
                    select1.setText(LandingScreenActivity.employeeBob.completeTask(0));
                }
                else
                {
                    select1.setText(LandingScreenActivity.employeeKyra.completeTask(0));
                }
            }
            else
            {
                if (LandingScreenActivity.employeeCole.getIsTracked())
                {
                    select2.setText(LandingScreenActivity.employeeCole.completeTask(1));
                }
                else if (LandingScreenActivity.employeeBob.getIsTracked())
                {
                    select2.setText(LandingScreenActivity.employeeBob.completeTask(1));
                }
                else
                {
                    select2.setText(LandingScreenActivity.employeeKyra.completeTask(1));
                }
            }
        }
    }

    public void showTasks()
    {
        if (LandingScreenActivity.employeeCole.getIsTracked())
        {
            if (!EmployerTaskActivity.c1) {
                select1.setText(LandingScreenActivity.employeeCole.getTask1());
            } else {
                select1.setVisibility(View.GONE);
            }

            if (!EmployerTaskActivity.c2) {
                select2.setText(LandingScreenActivity.employeeCole.getTask2());
            } else {
                select2.setVisibility(View.GONE);
            }
        }
        else if (LandingScreenActivity.employeeBob.getIsTracked())
        {
            if (!EmployerTaskActivity.b1) {
                select1.setText(LandingScreenActivity.employeeBob.getTask1());
            } else {
                select1.setVisibility(View.GONE);
            }

            if (!EmployerTaskActivity.b2) {
                select2.setText(LandingScreenActivity.employeeBob.getTask2());
            } else {
                select2.setVisibility(View.GONE);
            }
        }
        else
        {
            if (!EmployerTaskActivity.k1) {
                select1.setText(LandingScreenActivity.employeeKyra.getTask1());
            } else {
                select1.setVisibility(View.GONE);
            }

            if (!EmployerTaskActivity.k2) {
                select2.setText(LandingScreenActivity.employeeKyra.getTask2());
            } else {
                select2.setVisibility(View.GONE);
            }
        }
    }

    public void trackTask()
    {
        if (LandingScreenActivity.employeeCole.getIsTracked())
        {
            if ((select2.isChecked())) {
                LandingScreenActivity.employeeCole.setOnTask2(true);
            } else {
                LandingScreenActivity.employeeCole.setOnTask1(true);
            }
        }
        else if (LandingScreenActivity.employeeBob.getIsTracked())
        {
            if ((select2.isChecked())) {
                LandingScreenActivity.employeeBob.setOnTask2(true);
            } else {
                LandingScreenActivity.employeeBob.setOnTask1(true);
            }
        }
        else
        {
            if ((select2.isChecked())) {
                LandingScreenActivity.employeeKyra.setOnTask2(true);
            } else {
                LandingScreenActivity.employeeKyra.setOnTask1(true);
            }
        }
    }
}
