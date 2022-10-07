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
import com.app.classattendanceapp.state.GlobalState;
import com.google.android.material.textfield.TextInputEditText;

public class EditStudent extends AppCompatActivity {
    TextInputEditText studentID, firstName, lastName, program;
    RadioButton maleButton, femaleButton;

    Student g = GlobalState.tmpStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        initVariables();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Student");
        getSupportActionBar().setSubtitle("Update the form below");
    }

    private void initVariables(){
        studentID = findViewById(R.id.student_id_input);
        firstName = findViewById(R.id.first_name_input);
        lastName = findViewById(R.id.last_name_input);
        program = findViewById(R.id.program_input);

        studentID.setText(g.getStudentID());
        firstName.setText(g.getFirstName());
        lastName.setText(g.getLastName());
        program.setText(g.getProgram());

        maleButton = findViewById(R.id.male_button);
        femaleButton = findViewById(R.id.female_button);

        if(g.getGender().equals("Male")){
            maleButton.setChecked(true);
        }else if(g.getGender().equals("Female")){
            femaleButton.setChecked(true);
        }
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

    public void editStudent(View v)
    {
        if(this.areFieldsValid()){
            String previousID = g.getStudentID();

            g.setStudentID(studentID.getText().toString());
            g.setFirstName(firstName.getText().toString());
            g.setLastName(lastName.getText().toString());
            g.setProgram(program.getText().toString());

            StudentModel model = new StudentModel(this);
            model.update(g, previousID);

            Toast.makeText(this, "Student Successfully Edited", Toast.LENGTH_LONG).show();

            finish();
        }else {
            Toast.makeText(this, "Please fill in all the fields.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean areFieldsValid()
    {
        if(
            studentID.getText().toString() == "" ||
            firstName.getText().toString() == "" ||
            lastName.getText().toString() == "" ||
            program.getText().toString() == "" ||
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