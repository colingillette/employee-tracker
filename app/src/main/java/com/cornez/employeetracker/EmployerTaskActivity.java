package com.cornez.employeetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class EmployerTaskActivity extends AppCompatActivity
{
    public static boolean c1 = false, c2 = false, b1 = false, b2 = false, k1 = false, k2 = false;

    public int whichEmployee;
    public int id;

    RelativeLayout rel;

    Button btnClear;
    Button btnBack;

    EditText edit;
    CheckBox first;
    CheckBox second;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employer_task_view);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        btnClear = (Button) findViewById(R.id.taskViewClearButton);
        btnBack = (Button) findViewById(R.id.taskViewBackButton);

        rel = (RelativeLayout) findViewById(R.id.employerTaskViewLayout);

        first = (CheckBox) findViewById(R.id.checkBox1);
        second = (CheckBox) findViewById(R.id.checkBox2);

        edit = (EditText) findViewById(R.id.editText);

        whichEmployee = getTrackedEmployee();
        showTasks();
        editTask();
    }

    //Transition between layouts
    public void changeLayout(View view)
    {
        View.OnClickListener handler = new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (v == btnClear)
                {
                    clearChecked();
                }
                if (v == btnBack)
                {
                    Intent intentMain = new Intent(EmployerTaskActivity.this,
                            EmployerViewActivity.class);
                    EmployerTaskActivity.this.startActivity(intentMain);
                }
            }
        };

//        btnEdit.setOnClickListener(handler);
        btnClear.setOnClickListener(handler);
        btnBack.setOnClickListener(handler);
    }

    public void clearChecked()
    {
        if (first.isChecked())
        {
            rel.removeView(first);
            deleteTask1();
        }
        if (second.isChecked())
        {
            rel.removeView(second);
            deleteTask2();
        }
    }

    public int getTrackedEmployee()
    {
        if (LandingScreenActivity.employeeCole.getIsTracked())
        {
            id = 0;
        }
        else if (LandingScreenActivity.employeeBob.getIsTracked())
        {
            id = 1;
        }
        else
        {
            id = 2;
        }

        return id;
    }

    public void showTasks()
    {
        String complete = "Completed: ";

        switch (whichEmployee)
        {
            case 0:
                if (!(LandingScreenActivity.employeeCole.t1IsDeleted()) && !(LandingScreenActivity.employeeCole.task1AlreadyComplete)) {
                    first.setText(LandingScreenActivity.employeeCole.getTask1());
                } else if (!(LandingScreenActivity.employeeCole.t1IsDeleted()) && (LandingScreenActivity.employeeCole.task1AlreadyComplete)) {
                    first.setText(complete +  LandingScreenActivity.employeeCole.getTask1());
                } else {
                    rel.removeView(first);
                }

                if (!(LandingScreenActivity.employeeCole.t2IsDeleted()) && !(LandingScreenActivity.employeeCole.task2AlreadyComplete)) {
                    second.setText(LandingScreenActivity.employeeCole.getTask2());
                } else if (!(LandingScreenActivity.employeeCole.t2IsDeleted()) && (LandingScreenActivity.employeeCole.task2AlreadyComplete)) {
                    first.setText(complete + LandingScreenActivity.employeeCole.getTask2());
                } else {
                    rel.removeView(second);
                }
                break;
            case 1:
                if (!(LandingScreenActivity.employeeBob.t1IsDeleted()) && !(LandingScreenActivity.employeeBob.task1AlreadyComplete)) {
                    first.setText(LandingScreenActivity.employeeBob.getTask1());
                } else if (!(LandingScreenActivity.employeeBob.t1IsDeleted()) && (LandingScreenActivity.employeeBob.task1AlreadyComplete)) {
                    first.setText(complete +  LandingScreenActivity.employeeBob.getTask1());
                } else {
                    rel.removeView(first);
                }

                if (!(LandingScreenActivity.employeeBob.t2IsDeleted()) && !(LandingScreenActivity.employeeBob.task2AlreadyComplete)) {
                    second.setText(LandingScreenActivity.employeeBob.getTask2());
                } else if (!(LandingScreenActivity.employeeBob.t2IsDeleted()) && (LandingScreenActivity.employeeBob.task2AlreadyComplete)) {
                    first.setText(complete + LandingScreenActivity.employeeBob.getTask2());
                } else {
                    rel.removeView(second);
                }
                break;
            case 2:
                if (!(LandingScreenActivity.employeeKyra.t1IsDeleted()) && !(LandingScreenActivity.employeeKyra.task1AlreadyComplete)) {
                    first.setText(LandingScreenActivity.employeeKyra.getTask1());
                }  else if (!(LandingScreenActivity.employeeKyra.t1IsDeleted()) && (LandingScreenActivity.employeeKyra.task1AlreadyComplete)) {
                    first.setText(complete + LandingScreenActivity.employeeKyra.getTask1());
                } else {
                    rel.removeView(first);
                }
                if (!(LandingScreenActivity.employeeKyra.t2IsDeleted()) && !(LandingScreenActivity.employeeKyra.task2AlreadyComplete)) {
                    second.setText(LandingScreenActivity.employeeKyra.getTask2());
                } else if (!(LandingScreenActivity.employeeKyra.t2IsDeleted()) && (LandingScreenActivity.employeeKyra.task2AlreadyComplete)) {
                    first.setText(complete + LandingScreenActivity.employeeKyra.getTask2());
                } else {
                    rel.removeView(second);
                }
                break;
        }
    }

    public void deleteTask1()
    {
        switch (whichEmployee)
        {
            case 0:
                LandingScreenActivity.employeeCole.deleteTask(0);
                LandingScreenActivity.employeeCole.t1IsDeleted();
                c1 = true;
                break;
            case 1:
                LandingScreenActivity.employeeBob.deleteTask(0);
                LandingScreenActivity.employeeBob.t1IsDeleted();
                b1 = true;
                break;
            case 2:
                LandingScreenActivity.employeeKyra.deleteTask(0);
                LandingScreenActivity.employeeKyra.t1IsDeleted();
                k1 = true;
                break;
        }
    }

    public void deleteTask2()
    {
        switch (whichEmployee)
        {
            case 0:
                LandingScreenActivity.employeeCole.deleteTask(1);
                LandingScreenActivity.employeeCole.t2IsDeleted();
                c2 = true;
                break;
            case 1:
                LandingScreenActivity.employeeBob.deleteTask(1);
                LandingScreenActivity.employeeBob.t2IsDeleted();
                b2 = true;
                break;
            case 2:
                LandingScreenActivity.employeeKyra.deleteTask(1);
                LandingScreenActivity.employeeKyra.t2IsDeleted();
                k2 = true;
                break;
        }
    }

    public void editTask()
    {
        TextWatcher inputWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (first.isChecked())
                {
                    switch (whichEmployee)
                    {
                        case 0:
                            LandingScreenActivity.employeeCole.setTask1(s.toString());
                            first.setText(LandingScreenActivity.employeeCole.getTask1());
                            break;
                        case 1:
                            LandingScreenActivity.employeeBob.setTask1(s.toString());
                            first.setText(LandingScreenActivity.employeeBob.getTask1());
                            break;
                        case 2:
                            LandingScreenActivity.employeeKyra.setTask1(s.toString());
                            first.setText(LandingScreenActivity.employeeKyra.getTask1());
                            break;
                    }
                }
                if (second.isChecked())
                {
                    switch (whichEmployee)
                    {
                        case 0:
                            LandingScreenActivity.employeeCole.setTask2(s.toString());
                            second.setText(LandingScreenActivity.employeeCole.getTask2());
                            break;
                        case 1:
                            LandingScreenActivity.employeeBob.setTask2(s.toString());
                            second.setText(LandingScreenActivity.employeeBob.getTask2());
                            break;
                        case 2:
                            LandingScreenActivity.employeeKyra.setTask2(s.toString());
                            second.setText(LandingScreenActivity.employeeKyra.getTask2());
                            break;
                    }

                }
            }
        };

        edit.addTextChangedListener(inputWatcher);
    }
}
