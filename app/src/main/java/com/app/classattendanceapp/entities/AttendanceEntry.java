package com.app.classattendanceapp.entities;

import java.util.Objects;

public class AttendanceEntry
{
    private Student student;
    private String status; // Present | Present But Late | Absent | Absent with Permission

    public AttendanceEntry(Student student, String status) {
        this.student = student;
        this.status = status;
    }

    public AttendanceEntry() {
        this.student = null;
        this.status = "";
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AttendanceEntry{" +
                "student=" + student +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttendanceEntry that = (AttendanceEntry) o;

        if (!Objects.equals(student, that.student)) return false;
        return status.equals(that.status);
    }

    @Override
    public int hashCode() {
        int result = student != null ? student.hashCode() : 0;
        result = 31 * result + status.hashCode();
        return result;
    }
}
