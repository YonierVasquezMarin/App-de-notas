package com.midominio.appdenotas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText inputNoteTitle, inputNoteBody;
    private ImageButton btnSaveNote, btnUploadNote, btnClearFields, btnDeleteNote, btnShowAllNotes;
    private NotesHandler notesHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesHandler = new NotesHandler();

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
                getNote();
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
        String noteTitle = inputNoteTitle.getText().toString();
        String noteBody = inputNoteBody.getText().toString();

        boolean continueMethod = checkFields(new String[]{noteTitle, noteBody}, "Ingrese titulo y cuerpo de la nota");

        if(continueMethod) {
            Note userNote = new Note(noteTitle, noteBody);
            this.notesHandler.saveNoteInTheFile(userNote);
            Toast.makeText(this, "Guardada correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Recoge el titulo del EditText de la actividad, y con ese String busca entre los
     * titulos guardados en la targeta SD. Después, trae el cuerpo de la nota y lo pone
     * en el segundo EditText de la actividad.
     */
    private void getNote() {
        String noteTitle = this.inputNoteTitle.getText().toString();

        boolean continueMethod = this.checkFields(new String[]{noteTitle}, "Especifica un titulo");
        if (continueMethod) {

            Note noteToGet = this.notesHandler.getNoteOfTheList(noteTitle);
            if(noteToGet != null) {
                String noteBody = noteToGet.getMessageBody();
                this.inputNoteBody.setText(noteBody);
            } else {
                Toast.makeText(this, "La nota no existe", Toast.LENGTH_SHORT).show();
            }

        }
    }

    /**
     * Limpia los dos campos de la actividad.
     */
    private void clearFields() {
        this.inputNoteTitle.setText("");
        this.inputNoteBody.setText("");
    }

    /**
     * Busca la nota por su titulo. El titulo está en el primer EditText de la actividad.
     * Luego, busca la nota en la targeta SD y la borra.
     */
    private void deleteNote() {
        String noteTitle = this.inputNoteTitle.getText().toString();

        boolean continueMethod = this.checkFields(new String[]{noteTitle}, "Especifica un titulo");
        if (continueMethod) {
            if (this.notesHandler.deleteNoteOfTheFile(noteTitle)) {
                Toast.makeText(this, "La nota se eliminó", Toast.LENGTH_SHORT).show();
                this.inputNoteTitle.setText("");
                this.inputNoteBody.setText("");
            } else {
                Toast.makeText(this, "La nota no se encuentra", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Extrae todos los titulos de las notas guardadas en targeta SD, y muestra estos
     * titulos en el segundo EditText de la actividad.
     */
    private void showAllNotes() {
        ArrayList<String> notesTitleOfAllTheNotes = this.notesHandler.getTitlesOfAllTheNotes();
        String messageBody = "";
        if(notesTitleOfAllTheNotes != null && notesTitleOfAllTheNotes.size()!=0) {
            for (String noteTitle : notesTitleOfAllTheNotes) {
                messageBody += noteTitle + "\n";
            }
        } else {
            Toast.makeText(this, "No hay notas guardadas", Toast.LENGTH_SHORT).show();
        }
        this.inputNoteBody.setText(messageBody);
    }

    /**
     * Chequea que todos los Strings del arreglo del primer parametro contengan algun texto.
     * Si al menos un String del arreglo está vacío, se muestra un Toast.
     * @param editTextStrings un arreglo de Strings, los cuales son los textos de las
     *                       entradas de los EditText de la actividad.
     * @param errorMessage El mensaje de error que se muestra si al menos un String del arreglo
     *                     no contiene carateres. Se muestra en un Toast.
     * @return retorna "true" si todos los Strings del arreglo contienen algún carater. Retorna
     * "false" si al menos un String del arreglo no tiene carateres.
     */
    private boolean checkFields(String[] editTextStrings, String errorMessage) {
        for (String editTextString : editTextStrings) {
            if(editTextString.isEmpty()) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }
}