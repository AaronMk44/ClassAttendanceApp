package com.app.classattendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.app.classattendanceapp.entities.Course;
import com.app.classattendanceapp.models.CourseModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class TakeAttendance extends AppCompatActivity {
    DatePickerDialog datePicker;
    Button openDatePickerBtn;
    AutoCompleteTextView customSpinner;
    TextInputEditText selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);

        // Init UI
        openDatePickerBtn = findViewById(R.id.pick_time_button);
        selectedDate = findViewById(R.id.selected_date);
        customSpinner = findViewById(R.id.customer_course_spinner);

        // Setup Spinner Items
        CourseModel cm = new CourseModel(this);
        List<Course> cList = cm.getAll();
        List<String> courses = new ArrayList<>();
        for (Course c:
             cList) {
            courses.add(c.getListViewable());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, courses);

        customSpinner.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle("Take Attendance");
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

    public void pickDate(View v){
        datePicker = new DatePickerDialog(this, (picker, y, m, d) -> {
            selectedDate.setText(d + "/" + m + "/" + y);
        }, 2022, 02, 01);
        datePicker.show();
    }
    
    public void startTakingAttendance(View v){
        if(
            customSpinner.getText().toString().equals("") ||
            selectedDate.getText().toString().equals("")
        ){
            Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }
}