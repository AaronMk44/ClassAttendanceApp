package com.app.classattendanceapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.classattendanceapp.entities.Course;
import com.app.classattendanceapp.entities.Student;
import com.app.classattendanceapp.models.CourseModel;
import com.app.classattendanceapp.models.StudentModel;
import com.app.classattendanceapp.state.GlobalState;

import java.util.ArrayList;
import java.util.List;

public class CoursesFragment extends Fragment {
    Context context;
    List<Course> databaseCourseList;
    ListView myListView;
    ArrayList<String> courseArrayList;
    ArrayAdapter<String> adapter;
    public CoursesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        courseArrayList = new ArrayList<>();
        context = getActivity();
        databaseCourseList = new CourseModel(context).getAll();

        int index = 0;
        for (Course c: databaseCourseList) {
            courseArrayList.add(++index + ". " + c.getListViewable());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        databaseCourseList.clear();
        courseArrayList.clear();
        databaseCourseList = new CourseModel(context).getAll();

        int index = 0;
        for (Course c: databaseCourseList) {
            courseArrayList.add(++index + ". " + c.getListViewable());
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_courses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_list_item_1,
                courseArrayList
        );
        myListView = view.findViewById(R.id.course_list);
        myListView.setAdapter(adapter);

        myListView.setOnItemLongClickListener((parent, v, position, id) -> {
            GlobalState.tmpCourse = databaseCourseList.get(position);
            return false;
        });
        registerForContextMenu(myListView);
    }

    @Override
    public void onCreateContextMenu(
            @NonNull ContextMenu menu,
            @NonNull View v,
            @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.course_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit_course_mi:
                Intent intent = new Intent(context, EditCourse.class);
                startActivity(intent);
                return true;
            case R.id.view_enrolled_students_mi:
                return true;
            case R.id.enroll_students_mi:
                return true;
            case R.id.unenroll_students_mi:
                return true;
            default: break;
        }

        return super.onContextItemSelected(item);
    }
}