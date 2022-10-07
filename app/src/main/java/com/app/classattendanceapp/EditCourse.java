package com.app.classattendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.app.classattendanceapp.entities.Course;
import com.app.classattendanceapp.models.CourseModel;
import com.app.classattendanceapp.state.GlobalState;
import com.google.android.material.textfield.TextInputEditText;

public class EditCourse extends AppCompatActivity {
    TextInputEditText courseCode, courseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        courseCode = findViewById(R.id.course_code);
        courseName = findViewById(R.id.course_name);

        courseCode.setText(GlobalState.tmpCourse.getCourseCode());
        courseName.setText(GlobalState.tmpCourse.getCourseName());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Course");
        getSupportActionBar().setSubtitle("Fill the form below");
    }

    public void editCourse(View v){
        Toast.makeText(this, GlobalState.tmpCourse.getCourseCode(), Toast.LENGTH_SHORT).show();
        if(
            courseCode.getText().toString().equals("") ||
            courseName.getText().toString().equals("")
        ){
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }else{

            GlobalState.tmpCourse.setCourseCode(courseCode.getText().toString());
            GlobalState.tmpCourse.setCourseName(courseName.getText().toString());

            Context context = getApplicationContext();
            CourseModel cm = new CourseModel(context);
            cm.update(GlobalState.tmpCourse);
            Toast.makeText(this, "Course Successfully Updated", Toast.LENGTH_LONG).show();
            GlobalState.clearState();
            finish();
        }
    }
}