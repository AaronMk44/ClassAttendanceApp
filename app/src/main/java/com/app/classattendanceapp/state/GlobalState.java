package com.app.classattendanceapp.state;

import com.app.classattendanceapp.entities.Course;
import com.app.classattendanceapp.entities.Student;

public class GlobalState {
    public static final String DATABASE_NAME = "test.sqlite";
    public static final int DATABASE_VERSION = 2;
    public static Student tmpStudent = null;
    public static Course tmpCourse = null;
    public static void clearState(){
        tmpCourse = null;
        tmpCourse = null;
    }
}
