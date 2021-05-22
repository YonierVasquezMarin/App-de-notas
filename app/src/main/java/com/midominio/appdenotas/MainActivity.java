package com.midominio.appdenotas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private EditText inputNoteTitle, inputNoteBody;

    private ImageButton btnSaveNote, btnUploadNote, btnClearFields, btnDeleteNote, btnShowAllNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNoteTitle = (EditText) findViewById(R.id.inputNoteTitle);
        inputNoteBody = (EditText) findViewById(R.id.inputNoteBody);

        btnSaveNote = (ImageButton) findViewById(R.id.btnSaveNote);
        btnUploadNote = (ImageButton) findViewById(R.id.btnUploadNote);
        btnClearFields = (ImageButton) findViewById(R.id.btnClearFields);
        btnDeleteNote = (ImageButton) findViewById(R.id.btnDeleteNote);
        btnShowAllNotes = (ImageButton) findViewById(R.id.btnShowAllNotes);
    }
}