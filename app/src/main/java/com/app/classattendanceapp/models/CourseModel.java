package com.app.classattendanceapp.models;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.app.classattendanceapp.entities.Course;
import com.app.classattendanceapp.entities.Student;
import com.app.classattendanceapp.state.GlobalState;

import java.util.ArrayList;
import java.util.List;

public class CourseModel extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = GlobalState.DATABASE_NAME;
    private static final int DATABASE_VERSION = GlobalState.DATABASE_VERSION;
    private static final String TABLE_NAME = "courses";

    public CourseModel(
            @Nullable Context context
    ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String DDL = "CREATE TABLE " + TABLE_NAME +
                " (" +
                    "course_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "course_code TEXT NOT NULL," +
                    "course_name TEXT NOT NULL" +
                ")";
        db.execSQL(DDL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion) {
            db.execSQL("drop table if exists " + TABLE_NAME);
            onCreate(db);
        }
    }

    public void add(Course o)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("course_code", o.getCourseCode());
        values.put("course_name", o.getCourseName());


        try{
            db.insert(TABLE_NAME, null, values);
        }catch (Exception e){
            onCreate(db);
            db.insert(TABLE_NAME, null, values);
        }finally {
            db.close();
        }
    }

    public List<Course> getAll()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        List<Course> coursesList = new ArrayList<>();


        if (cursor.moveToFirst()) {
            do {
                Course c = new Course();
                c.setCourseID(Integer.parseInt(cursor.getString(0)));
                c.setCourseCode(cursor.getString(1));
                c.setCourseName(cursor.getString(2));

                coursesList.add(c);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return coursesList;
    }

    public int update(Course o) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("course_code", o.getCourseCode());
        values.put("course_name", o.getCourseName());

        return db.update(
                TABLE_NAME,
                values,
                "course_id = ?",
                new String[] { ""+o.getCourseID() }
        );
    }
}
