package com.app.classattendanceapp.entities;

public class Student {
    private String studentID;
    private String firstName;
    private String lastName;
    private String gender;
    private String program;

    public Student(
            String studentID,
            String firstName,
            String lastName,
            String gender,
            String program
    ) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.program = program;
    }

    public Student() {
        this.studentID = "";
        this.firstName = "";
        this.lastName = "";
        this.gender = "";
        this.program = "";
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getListViewableStudent()
    {
        return this.firstName + " " + this.lastName + " | " + this.studentID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (!studentID.equals(student.studentID)) return false;
        if (!firstName.equals(student.firstName)) return false;
        if (!lastName.equals(student.lastName)) return false;
        if (!gender.equals(student.gender)) return false;
        return program.equals(student.program);
    }

    @Override
    public int hashCode() {
        int result = studentID.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + gender.hashCode();
        result = 31 * result + program.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", program='" + program + '\'' +
                '}';
    }
}
