package com.example.projectv2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projectv2.R;
import com.example.projectv2.adapters.NoteRVAdapter;
import com.example.projectv2.entities.Note;
import com.example.projectv2.handler.NoteHandler;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Note> noteArrayList;
    private NoteHandler noteHandler;
    private NoteRVAdapter noteRVAdapter;
    private RecyclerView recyclerView;
    private EditText edt_inputSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_inputSearch = findViewById(R.id.edt_inputSearch);

        noteArrayList = new ArrayList<>();
        noteHandler = new NoteHandler(MainActivity.this);

        noteArrayList = noteHandler.readNotes();

        noteRVAdapter = new NoteRVAdapter(noteArrayList, MainActivity.this);
        recyclerView =findViewById(R.id.rc_ListNote);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, recyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(noteRVAdapter);

        ImageView img_addNoteMain = findViewById(R.id.img_AddNoteButtonMain);
        img_addNoteMain.setOnClickListener((v) -> {
            Intent i = new Intent(MainActivity.this, CreateNote.class);
            startActivity(i);
        });

        edt_inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchNote(edt_inputSearch.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void searchNote (String search){

        noteArrayList = new ArrayList<>();
        noteHandler = new NoteHandler(MainActivity.this);

        if(search == ""){
            noteArrayList = noteHandler.readNotes();
        }else {
            noteArrayList = noteHandler.searchNote(search);
        }

        noteRVAdapter = new NoteRVAdapter(noteArrayList, MainActivity.this);
        recyclerView =findViewById(R.id.rc_ListNote);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, recyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(noteRVAdapter);
    }
}