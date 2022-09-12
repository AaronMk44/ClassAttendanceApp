package com.app.classattendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.classattendanceapp.entities.Student;
import com.app.classattendanceapp.models.StudentModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity{
    FloatingActionButton addStudent, addCourse, takeAttendance, addAction;
    TextView takeAttendanceText, addStudentText, addCourseText;
    boolean areFabShowing;

    BottomNavigationView navBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // START: Action Button Logic
        takeAttendance = findViewById(R.id.take_attendance);
        addStudent = findViewById(R.id.add_student);
        addCourse = findViewById(R.id.add_course);
        addAction = findViewById(R.id.add_action);

        takeAttendanceText = findViewById(R.id.take_attendance_text);
        addStudentText = findViewById(R.id.add_student_text);
        addCourseText = findViewById(R.id.add_course_text);

        takeAttendance.setVisibility(View.GONE);
        addStudent.setVisibility(View.GONE);
        addCourse.setVisibility(View.GONE);
        takeAttendanceText.setVisibility(View.GONE);
        addStudentText.setVisibility(View.GONE);
        addCourseText.setVisibility(View.GONE);

        // END: Action Button Logic

        // Start Navigation Bar Logic

        StudentsFragment studentFragment = new StudentsFragment();

        navBar = findViewById(R.id.bottom_navigation_view);
        navBar.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    Toast.makeText(this, "Clicked home", Toast.LENGTH_LONG).show();
                    return true;

                case R.id.attendance:
                    Toast.makeText(this, "Clicked attendance", Toast.LENGTH_LONG).show();
                    return true;

                case R.id.courses:
                    Toast.makeText(this, "Clicked courses", Toast.LENGTH_LONG).show();
                    return true;

                case R.id.students:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, studentFragment).commit();
                    return true;
            }
            return false;
        });
        // END: End Navigation Bar Logic
    }

    public void handleAddStudentAction(View v)
    {
        Intent intent = new Intent(this, CreateStudent.class);
        startActivity(intent);
    }

    public void handleAddAction(View view)
    {
        areFabShowing = false;

        if(areFabShowing){
            takeAttendance.hide();
            addStudent.hide();
            addCourse.hide();
            takeAttendanceText.setVisibility(View.GONE);
            addStudentText.setVisibility(View.GONE);
            addCourseText.setVisibility(View.GONE);

            areFabShowing = false;

        }else {
            takeAttendance.show();
            addStudent.show();
            addCourse.show();
            takeAttendanceText.setVisibility(View.VISIBLE);
            addStudentText.setVisibility(View.VISIBLE);
            addCourseText.setVisibility(View.VISIBLE);

            areFabShowing = true;
        }
    }
}