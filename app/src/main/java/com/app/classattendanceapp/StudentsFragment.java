package com.app.classattendanceapp;

import android.content.Context;
import android.content.Intent;
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
import com.app.classattendanceapp.state.GlobalState;

import java.util.ArrayList;
import java.util.List;

public class StudentsFragment extends Fragment {
    Context context;
    List<Student> databaseStudentList;
    ListView myListView;
    ArrayList<String> studentArrayList;
    ArrayAdapter<String> adapter;

    public StudentsFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentArrayList = new ArrayList<>();
        context = getActivity();
        databaseStudentList = new StudentModel(context).getAll();

        int index = 0;
        for (Student c: databaseStudentList) {
            studentArrayList.add(++index + ". " + c.getListViewableStudent());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        databaseStudentList.clear();
        studentArrayList.clear();
        databaseStudentList = new StudentModel(context).getAll();

        int index = 0;
        for (Student c: databaseStudentList) {
            studentArrayList.add(++index + ". " + c.getListViewableStudent());
        }
        adapter.notifyDataSetChanged();
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
         adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_list_item_1,
                 studentArrayList
        );
        myListView = view.findViewById(R.id.student_list);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener((parent, v, position, id) -> {
            GlobalState.tmpStudent = databaseStudentList.get(position);

            Intent intent = new Intent(context, EditStudent.class);
            startActivity(intent);
        });

        super.onViewCreated(view, savedInstanceState);
    }
}