package com.app.classattendanceapp.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.app.classattendanceapp.entities.Student;
import com.app.classattendanceapp.state.GlobalState;

import java.util.ArrayList;
import java.util.List;

public class CourseRegisterModel extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = GlobalState.DATABASE_NAME;
    private static final int DATABASE_VERSION = GlobalState.DATABASE_VERSION;
    private static final String TABLE_NAME = "courses_register";

    public CourseRegisterModel(
            @Nullable Context context
    ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String DDL = "CREATE TABLE " + TABLE_NAME +
                " (" +
                    "cr_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "course_id_fk INTEGER," +
                    "student_id_fk TEXT" +
                ")";
        String SEEDER = "INSERT INTO " + TABLE_NAME + " " +
                "(course_id_fk, student_id_fk)" +
                "VALUES" +
                "(?,?)";

        db.execSQL(DDL);
        db.execSQL(SEEDER, new String[]{"0","0"});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion) {
            db.execSQL("drop table if exists " + TABLE_NAME);
            onCreate(db);
        }
    }

    public List<Student> getEnrolledStudents(int courseID) {
        String sql =
                "SELECT " +
                "students.student_id, " +
                "students.first_name, " +
                "students.last_name, " +
                "students.gender, " +
                "students.program " +
                "FROM " + TABLE_NAME + " " +
                "INNER JOIN students " +
                "ON "+ TABLE_NAME+".student_id_fk = students.student_id " +
                "WHERE "+TABLE_NAME+".course_id_fk = ? ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(courseID)});

        List<Student> enrolledStudents = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setStudentID(cursor.getString(0));
                student.setFirstName(cursor.getString(1));
                student.setLastName(cursor.getString(2));
                student.setGender(cursor.getString(3));
                student.setProgram(cursor.getString(4));

                enrolledStudents.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return enrolledStudents;
    }

    public void enrollStudent(int courseID, String studentID){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("course_id_fk", courseID);
        values.put("student_id_fk", studentID);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void unenrollStudent(int courseID, String studentID){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(
                TABLE_NAME,
                "course_id_fk = ? AND student_id_fk = ?",
                new String[] { String.valueOf(courseID), studentID }
        );

        db.close();
    }
}
