package com.app.classattendanceapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.classattendanceapp.entities.Student;
import com.app.classattendanceapp.models.StudentModel;

import java.util.List;

public class StudentsFragment extends Fragment {
    Context context;
    List<Student> studentList;
    ListView myStudentList;
    String[] studentArr;

    public StudentsFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();

        StudentModel model = new StudentModel(context);
        studentList = model.getAll();

        studentArr = new String[studentList.size()];
        for (int i = 0; i < studentList.size(); i++) {
            studentArr[i] = studentList.get(i).getListViewableStudent();
        }

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
             ViewGroup container,
             Bundle savedInstanceState
    ) {

        return inflater.inflate(R.layout.fragment_students, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_list_item_1,
                studentArr
        );
        myStudentList = view.findViewById(R.id.student_list);
        myStudentList.setAdapter(adapter);
        super.onViewCreated(view, savedInstanceState);
    }
}