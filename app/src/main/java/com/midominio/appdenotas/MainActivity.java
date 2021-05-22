package com.midominio.appdenotas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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

        //Los capturadores de click de los cuatro ImageButton
        View.OnClickListener cl1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        };
        btnSaveNote.setOnClickListener(cl1);

        View.OnClickListener cl2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadNote();
            }
        };
        btnUploadNote.setOnClickListener(cl2);

        View.OnClickListener cl3 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        };
        btnClearFields.setOnClickListener(cl3);

        View.OnClickListener cl4 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteNote();
            }
        };
        btnDeleteNote.setOnClickListener(cl4);

        View.OnClickListener cl5 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllNotes();
            }
        };
        btnShowAllNotes.setOnClickListener(cl5);
    }

    /**
     * Guarda una nota con su titulo y cuerpo en la targeta SD. El titulo y el
     * cuerpo lo extrae de la actividad.
     */
    private void saveNote() {

    }

    /**
     * Recoge el titulo del EditText de la actividad, y con ese String busca entre los
     * titulos guardados en la targeta SD. Después, trae el cuerpo de la nota y lo pone
     * en el segundo EditText de la actividad.
     */
    private void uploadNote() {

    }

    /**
     * Limpia los dos campos de la actividad.
     */
    private void clearFields() {

    }

    /**
     * Busca la nota por su titulo. El titulo está en el primer EditText de la actividad.
     * Luego, busca la nota en la targeta SD y la borra.
     */
    private void deleteNote() {

    }

    /**
     * Extrae todos los titulos de las notas guardadas en targeta SD, y muestra estos
     * titulos en el segundo EditText de la actividad.
     */
    private void showAllNotes() {

    }
}