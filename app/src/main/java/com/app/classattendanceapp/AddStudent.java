package com.app.classattendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.app.classattendanceapp.entities.Student;
import com.app.classattendanceapp.models.StudentModel;
import com.google.android.material.textfield.TextInputEditText;

public class AddStudent extends AppCompatActivity {
    TextInputEditText studentID, firstName, lastName, program;

    Student g = new Student();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        studentID = findViewById(R.id.student_id_input);
        firstName = findViewById(R.id.first_name_input);
        lastName = findViewById(R.id.last_name_input);
        program = findViewById(R.id.program_input);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Student");
        getSupportActionBar().setSubtitle("Fill the form below");
    }

    public void selectGender(View v)
    {
        boolean checked = ((RadioButton) v).isChecked();

        switch(v.getId()){
            case R.id.male_button:
                if(checked) g.setGender("Male");
                break;

            case R.id.female_button:
                if(checked) g.setGender("Female");
                break;
        }
    }

    public void createStudent(View v)
    {
        if(this.areFieldsValid()){
            g.setStudentID(studentID.getText().toString());
            g.setFirstName(firstName.getText().toString());
            g.setLastName(lastName.getText().toString());
            g.setProgram(program.getText().toString());

            StudentModel model = new StudentModel(this);
            model.add(g);
            Toast.makeText(this, "Student Successfully Created", Toast.LENGTH_LONG).show();
            finish();
        }else {
            Toast.makeText(this, "Please fill in all the fields.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean areFieldsValid()
    {
        if(
                studentID.getText().toString() == "" || firstName.getText().toString() == "" ||
                lastName.getText().toString() == "" || program.getText().toString() == "" ||
                g.getGender() == ""
        ){
            return false;
        }else{
            return true;
        }
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