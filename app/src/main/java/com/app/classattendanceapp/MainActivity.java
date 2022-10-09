package com.app.classattendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.classattendanceapp.models.CourseModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity{
    FloatingActionButton
            addStudent,
            addCourse,
            takeAttendance,
            addAction;
    TextView
            takeAttendanceText,
            addStudentText,
            addCourseText;
    boolean areFabShowing;

    BottomNavigationView navBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setSubtitle("Home");
        initVariables();
        setUpNavBarLogic();
    }

    private void initVariables(){
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

        areFabShowing = false;

    }

    private void setUpNavBarLogic(){
        StudentsFragment studentFragment = new StudentsFragment();
        CoursesFragment coursesFragment = new CoursesFragment();

        navBar = findViewById(R.id.bottom_navigation_view);
        navBar.setOnItemSelectedListener(item -> {
            hideFABs();
            switch (item.getItemId()) {
                case R.id.home:
                    getSupportActionBar().setSubtitle("Home");
                    Toast.makeText(this, "Clicked home", Toast.LENGTH_LONG).show();
                    return true;

                case R.id.attendance:
                    getSupportActionBar().setSubtitle("Attendance");
                    Toast.makeText(this, "Clicked attendance", Toast.LENGTH_LONG).show();
                    return true;

                case R.id.courses:
                    getSupportActionBar().setSubtitle("Courses");
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, coursesFragment).commit();
                    return true;

                case R.id.students:
                    getSupportActionBar().setSubtitle("Students");
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, studentFragment).commit();
                    return true;
            }
            return false;
        });
    }

    public void handleTakeAttendance(View v){
        Intent intent = new Intent(this, TakeAttendance.class);
        startActivity(intent);
    }

    public void handleAddStudentAction(View v){
        Intent intent = new Intent(this, AddStudent.class);
        startActivity(intent);
    }

    public void handleAddCourseAction(View v){
        Intent intent = new Intent(this, AddCourse.class);
        startActivity(intent);
    }

    public void handleAddAction(View view){
        if(areFabShowing){
            hideFABs();
        }else {
            showFABs();
        }
    }

    private void showFABs(){
        takeAttendance.show();
        addStudent.show();
        addCourse.show();
        takeAttendanceText.setVisibility(View.VISIBLE);
        addStudentText.setVisibility(View.VISIBLE);
        addCourseText.setVisibility(View.VISIBLE);

        areFabShowing = true;
    }

    private void hideFABs(){
        if(areFabShowing){
            takeAttendance.hide();
            addStudent.hide();
            addCourse.hide();
            takeAttendanceText.setVisibility(View.GONE);
            addStudentText.setVisibility(View.GONE);
            addCourseText.setVisibility(View.GONE);

            areFabShowing = false;
        }
    }
}