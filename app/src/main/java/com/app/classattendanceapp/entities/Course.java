package com.app.classattendanceapp.entities;

public class Course {
    private int courseID;
    private String courseCode;
    private String courseName;

    public Course(
            int courseID,
            String courseCode,
            String courseName
    ) {
        this.courseID = courseID;
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    public Course() {
        this.courseID = 0;
        this.courseCode = "";
        this.courseName = "";
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getListViewable(){
        return this.courseCode + " " + this.courseName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
