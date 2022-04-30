package com.example.projectv2.handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.projectv2.entities.Note;

import java.util.ArrayList;

public class NoteHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "note_db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "mynotes";
    private static final String ID_COL = "id";
    private static final String TITLE_COL = "title";
    private static final String DATETIME_COL = "datetime";
    private static final String SUBTITLE_COL = "subtitle";
    private static final String NOTETEXT_COL = "notetext";
    private static final String IMG_PATH_COL = "img_path";
    private static final String COLOR_COL = "color";

    public NoteHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE_COL + " TEXT,"
                + DATETIME_COL + " TEXT,"
                + SUBTITLE_COL + " TEXT,"
                + NOTETEXT_COL + " TEXT,"
                + IMG_PATH_COL + " TEXT,"
                + COLOR_COL + " TEXT)";

        sqLiteDatabase.execSQL(query);
    }

    public void addNote(String title, String datetime, String subtitle, String notetext, String img_path, String color) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TITLE_COL, title);
        values.put(DATETIME_COL, datetime);
        values.put(SUBTITLE_COL, subtitle);
        values.put(NOTETEXT_COL, notetext);
        values.put(IMG_PATH_COL, img_path);
        values.put(COLOR_COL, color);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public void addNote(String title, String datetime, String subtitle, String notetext) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TITLE_COL, title);
        values.put(DATETIME_COL, datetime);
        values.put(SUBTITLE_COL, subtitle);
        values.put(NOTETEXT_COL, notetext);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<Note> readNotes() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor noteCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<Note> NoteList = new ArrayList<>();

        if (noteCursor.moveToFirst()) {
            do {
                NoteList.add(new Note(
                        noteCursor.getInt(0),
                        noteCursor.getString(1),
                        noteCursor.getString(2),
                        noteCursor.getString(3),
                        noteCursor.getString(4),
                        noteCursor.getString(5),
                        noteCursor.getString(6)
                ));
            } while (noteCursor.moveToNext());
        }
        noteCursor.close();
        return NoteList;
    }

    public void updateNote(String id, String title, String subtitle,
                             String datetime, String notetext) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(TITLE_COL, title);
        values.put(SUBTITLE_COL, subtitle);
        values.put(DATETIME_COL, datetime);
        values.put(NOTETEXT_COL, notetext);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "id=?", new String[]{id});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
