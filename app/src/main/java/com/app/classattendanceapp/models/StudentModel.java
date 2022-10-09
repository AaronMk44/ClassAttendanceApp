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

public class StudentModel extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = GlobalState.DATABASE_NAME;
    private static final int DATABASE_VERSION = GlobalState.DATABASE_VERSION;
    private static final String TABLE_NAME = "students";

    public StudentModel(
            @Nullable Context context
    ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String DDL = "CREATE TABLE " + TABLE_NAME +
                " (" +
                    "student_id TEXT PRIMARY KEY," +
                    "first_name TEXT NOT NULL," +
                    "last_name TEXT NOT NULL, " +
                    "gender TEXT NOT NULL," +
                    "program TEXT NOT NULL" +
                ")";
        db.execSQL(DDL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion) {
            onCreate(db);
        }
    }

    public void add(Student o)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("student_id", o.getStudentID());
        values.put("first_name", o.getFirstName());
        values.put("last_name", o.getLastName());
        values.put("gender", o.getGender());
        values.put("program", o.getProgram());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Student> getAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        List<Student> studentList = new ArrayList<>();


        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setStudentID(cursor.getString(0));
                student.setFirstName(cursor.getString(1));
                student.setLastName(cursor.getString(2));
                student.setGender(cursor.getString(3));
                student.setProgram(cursor.getString(4));

                studentList.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return studentList;
    }

    public int update(Student o, String previousID) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("student_id", o.getStudentID());
        values.put("first_name", o.getFirstName());
        values.put("last_name", o.getLastName());
        values.put("gender", o.getGender());
        values.put("program", o.getProgram());

        return db.update(
                TABLE_NAME,
                values,
                "student_id = ?",
                new String[] { previousID }
        );
    }

    public void remove(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(
                TABLE_NAME,
                "student_id = ?",
                new String[] { student.getStudentID() }
        );

        db.close();
    }
}
