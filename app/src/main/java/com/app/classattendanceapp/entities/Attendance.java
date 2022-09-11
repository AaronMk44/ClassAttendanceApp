package com.app.classattendanceapp.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Attendance {
    private int attendanceID;
    private Course course;
    private List<AttendanceEntry> attendees;
    private String date;
    private String time;

    public Attendance(
            int attendanceID,
            Course course,
            List<AttendanceEntry> attendees,
            String date,
            String time
    ) {
        this.attendanceID = attendanceID;
        this.course = course;
        this.date = date;
        this.time = time;
    }
    public Attendance() {
        this.attendanceID = 0;
        this.course = null;
        this.attendees = new ArrayList<>();
        this.date = "";
        this.time = "";
    }

    public int getAttendanceID() {
        return attendanceID;
    }

    public void setAttendanceID(int attendanceID) {
        this.attendanceID = attendanceID;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void addEntry(AttendanceEntry o)
    {
        this.attendees.add(o);
    }

    public void editEntry(AttendanceEntry o)
    {
        // 1. Find the index of the entry we want to edit
        int index = 0;
        for(AttendanceEntry i: this.attendees)
        {
            if(i.equals(o)){
                break;
            }else{
                index++;
            }
        }

        // 2. Replace the entry at that index with the new entry
        this.attendees.set(index, o);
    }

    public void removeEntry(AttendanceEntry o)
    {
        this.attendees.remove(o);
    }

    public List<AttendanceEntry> getEntries()
    {
        return this.attendees;
    }
}
