package com.example.projectv2.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectv2.R;
import com.example.projectv2.handler.NoteHandler;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class VieworUpdateNote extends AppCompatActivity {

    private EditText edt_title, edt_subtitle, edt_notetext;
    private TextView tv_datetime;
    private ImageView img_back, img_save, img_delete;
    private String noteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewor_update_note);

        noteID = getIntent().getStringExtra("id");

        edt_title = findViewById(R.id.edt_titleNote);
        edt_subtitle = findViewById(R.id.edt_subtitleNote);
        tv_datetime = findViewById(R.id.tv_dateTime);
        edt_notetext = findViewById(R.id.edt_Note);
        img_back = findViewById(R.id.img_back);
        img_save = findViewById(R.id.img_save);
        img_delete = findViewById(R.id.img_delete);

        String title = getIntent().getStringExtra("title");
        String datetime = getIntent().getStringExtra("datetime");
        String subtitle = getIntent().getStringExtra("subtitle");
        String notetext = getIntent().getStringExtra("notetext");

        edt_title.setText(title);
        tv_datetime.setText(datetime);
        edt_subtitle.setText(subtitle);
        edt_notetext.setText(notetext);

        img_back.setOnClickListener(view -> onBackPressed());
        img_save.setOnClickListener(view -> updateNote(noteID));
        img_delete.setOnClickListener(view -> confirmDeleteNote(view,noteID));


    }

    private void updateNote(String id) {
        NoteHandler noteHandler = new NoteHandler(VieworUpdateNote.this);

        String title = edt_title.getText().toString();
        String subtitle = edt_subtitle.getText().toString();
        String datetime = tv_datetime.getText().toString();
        String notetext = edt_notetext.getText().toString();

        try {
            noteHandler.updateNote(id, title, subtitle, datetime, notetext);
            Toast.makeText(this, "Update successfully...", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(VieworUpdateNote.this, MainActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Update failed...", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteNote(String id) {
        NoteHandler noteHandler = new NoteHandler(VieworUpdateNote.this);
        try {
            noteHandler.deleteNote(id);
            Toast.makeText(this, "Delete successfully...", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(VieworUpdateNote.this, MainActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Delete failed...", Toast.LENGTH_SHORT).show();
        }
    }

    private void confirmDeleteNote(View view, String id){

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle(R.string.confirm);
        builder.setMessage(R.string.are_you_sure);
        builder.setCancelable(false);

        builder.setPositiveButton(R.string.ok, (dialogInterface, i) -> {
            deleteNote(id);
            dialogInterface.dismiss();
        });

        builder.setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
            Toast.makeText(getApplicationContext(), R.string.cancel, Toast.LENGTH_SHORT).show();
            dialogInterface.dismiss();
        });

        builder.show();
    }
}