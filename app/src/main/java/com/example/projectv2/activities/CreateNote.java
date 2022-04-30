package com.example.projectv2.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.projectv2.R;
import com.example.projectv2.handler.NoteHandler;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateNote extends AppCompatActivity {

    private EditText edt_titleNote, edt_subtitleNote, edt_noteText;
    private TextView tv_dateTime;
    private ImageView img_Note, img_save;
    private NoteHandler noteHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        edt_titleNote = findViewById(R.id.edt_titleNote);
        edt_subtitleNote = findViewById(R.id.edt_subtitleNote);
        edt_noteText = findViewById(R.id.edt_Note);
        tv_dateTime = findViewById(R.id.tv_dateTime);
        img_Note = findViewById(R.id.img_Note);
        noteHandler = new NoteHandler(CreateNote.this);

        ImageView img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(view -> onBackPressed());

        tv_dateTime.setText(
                new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault())
                        .format(new Date())
        );

        ImageView img_save = findViewById(R.id.img_save);
        img_save.setOnClickListener(view -> saveNote());



    }

    private void saveNote(){
        String title = edt_titleNote.getText().toString();
        String subtitle = edt_subtitleNote.getText().toString();
        String datetime = tv_dateTime.getText().toString();
        String notetext = edt_noteText.getText().toString();

        try {
            noteHandler.addNote(title,subtitle,datetime,notetext);
            Toast.makeText(this, "Thêm thành công...", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(this, "Thêm không thành công...", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(CreateNote.this, MainActivity.class);
        startActivity(intent);
    }

}

