package com.app.classattendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.app.classattendanceapp.entities.Course;
import com.app.classattendanceapp.models.CourseModel;
import com.google.android.material.textfield.TextInputEditText;

public class AddCourse extends AppCompatActivity {
    TextInputEditText courseCode, courseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        courseCode = findViewById(R.id.course_code);
        courseName = findViewById(R.id.course_name);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Course");
        getSupportActionBar().setSubtitle("Fill the form below");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:break;
        }
        return true;
    }

    public void addCourse(View v){

        if(courseCode.getText().toString().equals("") || courseName.getText().toString().equals("")){
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }else{
            Course c = new Course();
            c.setCourseCode(courseCode.getText().toString());
            c.setCourseName(courseName.getText().toString());

            Context context = getApplicationContext();
            CourseModel cm = new CourseModel(context);
            cm.add(c);
            Toast.makeText(this, "Course Successfully Created", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}