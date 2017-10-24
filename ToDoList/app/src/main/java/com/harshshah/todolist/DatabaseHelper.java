package com.harshshah.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by harshshah on 9/4/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "task_table";

    private static final String task_table = "task_table";
    private static final String COL1 = "TASK_DESCRIPTION";
    private static final String COL2 = "TASK_DATE";


    public DatabaseHelper(Context context) {
        super(context, task_table, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + task_table + "ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + "TEXT";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS" + task_table);
        onCreate(db);

    }

    public boolean addData(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, item);
        contentValues.put(COL2, item);

        Log.d(TAG, "addData: Adding " + item + "to" + task_table);

        long result = db.insert(task_table, null, contentValues);


        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }


    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT + FROM " + task_table;
        Cursor taskData = db.rawQuery(query, null);

        return taskData;

    }


}
