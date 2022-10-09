package com.app.classattendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.app.classattendanceapp.entities.Course;
import com.app.classattendanceapp.entities.Student;
import com.app.classattendanceapp.models.CourseRegisterModel;
import com.app.classattendanceapp.state.GlobalState;

import java.util.List;

public class EnrolledStudents extends AppCompatActivity {
    ListView myListView;
    ArrayAdapter<String> adapter;

    List<Student> enrolledStudents;
    List<String> studentArrayList;

    CourseRegisterModel crm;
    Course  course = GlobalState.tmpCourse;
    Student toBeUnEnrolled = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrolled_students);

        crm = new CourseRegisterModel(this);

        // -------------------------------------------------------------
        // Get Students enrolled in this course, then add them to list
        // via the ArrayList<String> object
        enrolledStudents = crm.getEnrolledStudents(course.getCourseID());
        for(Student s: enrolledStudents){
            studentArrayList.add(s.getListViewableStudent());
        }

        // -------------------------------------------------------------
        // Bind the fetched students to the ListView view
        adapter = new ArrayAdapter<>(
                 getApplicationContext(), android.R.layout.simple_list_item_1, studentArrayList
         );
        myListView = findViewById(R.id.enrolled_student_list);
        myListView.setAdapter(adapter);

        // -------------------------------------------------------------
        // Implement long click
        myListView.setOnItemLongClickListener((parent, v, position, id) -> {
            toBeUnEnrolled = enrolledStudents.get(position);
            return false;
        });
        registerForContextMenu(myListView);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Enrolled Students");
        getSupportActionBar().setSubtitle("Course | " + course.getCourseCode() + " " + course.getCourseName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        enrolledStudents.clear();
        studentArrayList.clear();

        enrolledStudents = crm.getEnrolledStudents(course.getCourseID());
        for(Student s: enrolledStudents){
            studentArrayList.add(s.getListViewableStudent());
        }
        adapter.notifyDataSetChanged();
    }

    // -------------------------------------------------------------
    // The two methods handle logic to deal with Enrolling a student
    // in a course

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.enroll_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.enroll_student_mi:
                Intent next = new Intent(this, EnrollStudentInCourse.class);
                startActivity(next);
                return true;
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // -------------------------------------------------------------
    // The next two methods to follow handle logic to deal
    // with un-enrolling a student

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.unenroll_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.unenroll_student_mi:
                crm.enrollStudent(course.getCourseID(), toBeUnEnrolled.getStudentID());
                toBeUnEnrolled = null;
                Toast.makeText(this, "Student Successfully Unenrolled", Toast.LENGTH_SHORT).show();
                return true;
            default:break;
        }
        return super.onContextItemSelected(item);
    }
}