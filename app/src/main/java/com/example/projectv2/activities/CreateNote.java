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

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateNote extends AppCompatActivity {

    private EditText edt_titleNote, edt_subtitleNote, edt_noteText;
    private TextView tv_dateTime;
    private ImageView img_Note, img_save, img_addImage, img_imageNote;
    private NoteHandler noteHandler;
    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        edt_titleNote = findViewById(R.id.edt_titleNote);
        edt_subtitleNote = findViewById(R.id.edt_subtitleNote);
        edt_noteText = findViewById(R.id.edt_Note);
        tv_dateTime = findViewById(R.id.tv_dateTime);
        img_Note = findViewById(R.id.img_Note);
        img_addImage = findViewById(R.id.img_addImage);
        img_imageNote = findViewById(R.id.img_Note);


        noteHandler = new NoteHandler(CreateNote.this);

        ImageView img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(view -> onBackPressed());

        tv_dateTime.setText(
                new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault())
                        .format(new Date())
        );

        ImageView img_save = findViewById(R.id.img_save);
        img_save.setOnClickListener(view -> saveNote());

        img_addImage.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(
                    getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        CreateNote.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_STORAGE_PERMISSION
                );
            } else {
                selectImage();
            }
        });

    }

    private void saveNote() {
        String title = edt_titleNote.getText().toString();
        String subtitle = edt_subtitleNote.getText().toString();
        String datetime = tv_dateTime.getText().toString();
        String notetext = edt_noteText.getText().toString();

        try {
            noteHandler.addNote(title, datetime, subtitle, notetext);
            Toast.makeText(this, "Thêm thành công...", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Thêm không thành công...", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(CreateNote.this, MainActivity.class);
        startActivity(intent);
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
            Toast.makeText(this, "select image", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_STORAGE_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                selectImage();
                Toast.makeText(this, "onreq", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Quyền truy cập vào mục hình ảnh bị từ chối...", Toast.LENGTH_SHORT).show();
            }
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE_SELECT_IMAGE && requestCode == RESULT_OK) {
//            if (data != null) {
//                Uri selectedImageUri = data.getData();
//                if (selectedImageUri != null) {
//                    try {
//                        InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
//                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                        img_imageNote.setImageBitmap(bitmap);
//                        img_imageNote.setVisibility(View.VISIBLE);
//                        Toast.makeText(this, "display img", Toast.LENGTH_SHORT).show();
//                    } catch (FileNotFoundException e) {
//                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        }
//    }
}

