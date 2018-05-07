# How to Link Pages

This example will use the link between LandingScreen and Employee/EmployerViews

## The Manifest

In AndroidManifest.xml the activities that are in the page already will require these new additions: 

```xml
<activity android:name=".EmployerViewActivity"
            android:parentActivityName=".LandingScreenActivity">
            <meta-data
                android:name="android.support.PARENT_ACTIVITY"
                android:value=".LandingScreenActivity" />
```

The new additions in this blockare the second line, and the entire meta-data addition. Notice that the parent activity goes in both the last and second line.

## The Parent Activity

What looks different in LandingScreen.java?

```java
public class LandingScreenActivity extends AppCompatActivity
{
    Button btnEmployer;
    Button btnEmployee;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_screen);

        btnEmployer = (Button) findViewById(R.id.landingEmployerButton);
        btnEmployee = (Button) findViewById(R.id.landingEmployeeButton);
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
}
```

Important notes: The buttons should be declared publicly at the top, and initialized in onCreate(). The parent activity should be the only thing in the handler except as the second parameter in the Intent intilization, which will be the new page that we're trying to direct to. 

## Children Activities

As an example, we will look at EmployeeViewActivity.java, and the important bit in relation to our needs in studying the parent/child relationship.

```java
@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.employee_view);
        btnIn = (Button) findViewById(R.id.empViewInButton);
        btnOut= (Button) findViewById(R.id.empViewOutButton);
    }
```

The second line of the method body references two anim files that are stored in res/anim. We will look at them later, but this method will call those animations so that they will be presented on screen when the button is clicked.

## Anim

These will not need to be recreated on all six activities, but I figured I'd include one of them for demo purposes. I will speed up the duration time in the future.

```xml
<?xml version="1.0" encoding="utf-8"?>
<alpha xmlns:android="http://schemas.android.com/apk/res/android"
    android:interpolator="@android:anim/accelerate_interpolator"
    android:fromAlpha="0.0" android:toAlpha="1.0" android:duration="500" />
```

That was anim_in. It is a simple fade in for the current page when it is called, and a fade out when it leaves.
