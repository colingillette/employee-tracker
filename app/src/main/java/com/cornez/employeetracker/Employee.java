package com.cornez.employeetracker;

// This class is designed to store data over the course of the app
public class Employee
{
    private int _id;
    private String _name;
    private String _task1;
    private String _task2;
    private boolean _onTask1;
    private boolean _onTask2;
    private boolean _isTracked;
    private String _time;

    public boolean task1AlreadyComplete = false, task2AlreadyComplete = false;

    public static boolean firstInstance = true, firstTimer = true;

    public Employee()
    {
        _id = 0;
        _name = "Default Employeeman";
        _task1 = "The first thing.";
        _task2 = "The second thing.";
        _onTask1 = false;
        _onTask2 = false;
        _isTracked = false;
        _time = "00:00:00";
    }

    public Employee(int id, String name, String task1, String task2)
    {
        _id = id;
        _name = name;
        _task1 = task1;
        _task2 = task2;
        _onTask1 = false;
        _onTask2 = false;
        _isTracked = false;
        _time = "00:00:00";
    }

    //Getters
    public int getId()
    {
        return this._id;
    }

    public String getName()
    {
        return this._name;
    }

    public String getTask1()
    {
        return this._task1;
    }

    public String getTask2()
    {
        return this._task2;
    }

    public boolean getOnTask1()
    {
        return  this._onTask1;
    }

    public boolean getOnTask2()
    {
        return  this._onTask2;
    }

    public boolean getIsTracked()
    {
        return this._isTracked;
    }

    public String get_time() { return this._time; }

    //Setters
    public void setId(int id)
    {
        this._id = id;
    }

    public void setName(String name)
    {
        this._name = name;
    }

    public void setTask1(String task)
    {
        this._task1 = task;
    }

    public void setTask2(String task)
    {
        this._task2 = task;
    }

    public void setOnTask1(boolean onTask)
    {
        this._onTask1 = onTask;
    }

    public void setOnTask2(boolean onTask)
    {
        this._onTask2 = onTask;
    }

    public void setIsTracked(boolean isTracked)
    {
        this._isTracked = isTracked;
    }

    public void setTime(String time) { this._time = time; }

    //Methods
    public String completeTask(int whichTask)
    {
        String complete = "Complete: ";
        String display;

        if (whichTask == 0)
        {
            if (!task1AlreadyComplete)
            {
                display = (complete + this.getTask1());
                task1AlreadyComplete = true;
                this.setOnTask1(false);
            }
            else
            {
                display = (this.getTask1());
                task1AlreadyComplete = false;
            }

            return display;
        }
        else
        {
            if (!task2AlreadyComplete)
            {
                display = (complete + this.getTask2());
                task2AlreadyComplete = true;
                this.setOnTask2(false);
            }
            else
            {
                display = (this.getTask2());
                task2AlreadyComplete = false;
            }

            return display;
        }
    }

    public void disableTracking()
    {
        this.setIsTracked(false);
        this.setOnTask1(false);
        this.setOnTask2(false);
    }

    public void deleteTask(int which)
    {
        if (which == 0)
        {
            this.setTask1("");
            this.setOnTask1(false);
        }
        else
        {
            this.setTask2("");
            this.setOnTask2(false);
        }
    }

    public boolean t1IsDeleted()
    {
        if (this.getTask1().equals(""))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean t2IsDeleted()
    {
        if (this.getTask2().equals(""))
        {
            return true;
        }
        else
        {
            return false;
        }
    }









    //Commented out test structures








    public static String previousLocation = "851 S John Q Hammons Pkwy,\nSpringfield, MO 65897";
}