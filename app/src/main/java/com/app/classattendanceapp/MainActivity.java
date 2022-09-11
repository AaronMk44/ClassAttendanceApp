package com.app.classattendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton addStudent, addCourse, addAction;
    TextView addStudentText, addCourseText;
    boolean areFabShowing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addStudent = findViewById(R.id.add_student);
        addCourse = findViewById(R.id.add_course);
        addAction = findViewById(R.id.add_action);

        addStudentText = findViewById(R.id.add_student_text);
        addCourseText = findViewById(R.id.add_course_text);

        addStudent.setVisibility(View.GONE);
        addCourse.setVisibility(View.GONE);
        addStudentText.setVisibility(View.GONE);
        addCourseText.setVisibility(View.GONE);

        areFabShowing = false;

        addAction.setOnClickListener(v -> {
            if(areFabShowing){
                addStudent.hide();
                addCourse.hide();
                addStudentText.setVisibility(View.GONE);
                addCourseText.setVisibility(View.GONE);

                areFabShowing = false;

            }else {
                addStudent.show();
                addCourse.show();
                addStudentText.setVisibility(View.VISIBLE);
                addCourseText.setVisibility(View.VISIBLE);

                areFabShowing = true;
            }
        });
    }

}