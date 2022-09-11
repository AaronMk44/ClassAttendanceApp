package com.app.classattendanceapp.entities;

import java.util.ArrayList;
import java.util.List;

public class CourseEnrollment
{
    private Course course;
    private List<Student> students;

    public CourseEnrollment(Course course) {
        this.course = course;
        this.students = new ArrayList<>();
    }

    public void setCourse(Course c)
    {
        this.course = c;
    }

    public Course getCourse()
    {
        return this.course;
    }

    public void enroll(Student o)
    {
        if(!this.students.contains(o)) this.students.add(o);
    }

    public void unEnroll(Student o)
    {
        if(this.students.contains(o)) this.students.remove(o);
    }

}
