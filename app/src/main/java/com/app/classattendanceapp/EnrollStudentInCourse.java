package com.app.classattendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.app.classattendanceapp.entities.Course;
import com.app.classattendanceapp.entities.Student;
import com.app.classattendanceapp.models.CourseRegisterModel;
import com.app.classattendanceapp.models.StudentModel;
import com.app.classattendanceapp.state.GlobalState;

import java.util.ArrayList;
import java.util.List;

public class EnrollStudentInCourse extends AppCompatActivity {
    ListView myListView;
    ArrayAdapter<String> adapter;

    List<Student> students;
    List<String> studentArrayList;

    CourseRegisterModel crm;
    StudentModel sm;
    Course course = GlobalState.tmpCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_student_in_course);

        crm = new CourseRegisterModel(this);
        sm = new StudentModel(this);


        // Get all Students then add them to list
        // via the ArrayList<String> object
        students = sm.getAll();
        studentArrayList = new ArrayList<>();
        for(Student s: students){
            studentArrayList.add(s.getListViewableStudent());
        }
        adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, studentArrayList
        );

        // Bind the fetched students to the ListView view
        myListView = findViewById(R.id.to_be_enrolled_student_list);
        myListView.setAdapter(adapter);
        
        myListView.setOnItemClickListener((parent, v, position, id) -> {
            // Logic to enroll
            Toast.makeText(this, "Student Successfully Enrolled", Toast.LENGTH_SHORT).show();
        });

        Toast.makeText(this, "Click on a Student to enroll", Toast.LENGTH_LONG).show();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Enroll Student");
        getSupportActionBar().setSubtitle("Course | " + course.getCourseCode() + " " + course.getCourseName());
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
}