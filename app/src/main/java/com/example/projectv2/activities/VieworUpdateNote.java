package com.example.projectv2.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.projectv2.R;
import com.example.projectv2.handler.NoteHandler;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class VieworUpdateNote extends AppCompatActivity {

    private EditText edt_title,edt_subtitle,edt_notetext;
    private TextView tv_datetime;
    private ImageView img_back,img_save,img_delete;
    private String noteID;
    private NoteHandler noteHandler;


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
        img_save.findViewById(R.id.img_save);

        edt_title.setText(getIntent().getStringExtra("title"));
        edt_subtitle.setText(getIntent().getStringExtra("subtitle"));
        edt_notetext.setText(getIntent().getStringExtra("notetext"));

        img_back.setOnClickListener(view -> onBackPressed());

        img_save.setOnClickListener(view -> updateNote(noteID));

        Toast.makeText(this, "id:" + getIntent().getStringExtra("id"), Toast.LENGTH_SHORT).show();

    }

    private void updateNote(String id){
        String title = edt_title.getText().toString();
        String subtitle = edt_subtitle.getText().toString();
        String datetime = tv_datetime.getText().toString();
        String notetext = edt_notetext.getText().toString();

        try {
            noteHandler.updateNote(id,title,subtitle,datetime,notetext);
            Toast.makeText(this, "Cập nhật thành công...", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(this, "Cập nhật không thành công...", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(VieworUpdateNote.this, MainActivity.class);
        startActivity(intent);
    }
}