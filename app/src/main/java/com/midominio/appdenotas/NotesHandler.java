package com.midominio.appdenotas;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Manejadore del archivo de notas.
 * @author Yonier Vasquez Marin
 * @version 1.0
 */
public class NotesHandler {
    private ArrayList<Note> arrNotes;
    private final String fileName = "notes.json";

    public NotesHandler() {

    }

    //test
    /**
     * Recibe una nota y la guarda en la ultima posición en la lista de notas, en el archivo
     * de notas de la targeta SD.
     * @param noteToSave La nota que se guarda en el archivo.
     */
    public void saveNote(Note noteToSave) {
        File SDTarget = Environment.getExternalStorageDirectory();
        File noteFile = new File(SDTarget.getAbsolutePath(), fileName);

        String notesContent = "";

        //leer el archivo
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(noteFile));
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            while (line!=null) {
                notesContent += line;
                line = br.readLine();
            }

            br.close();
            isr.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //Agrega al final de la lista de notas la nota a guardar
        try {
            Type noteCollectionType = new TypeToken<Collection<Note>>(){}.getType();
            Gson gson = new Gson();
            ArrayList<Note> noteList = gson.fromJson(notesContent, noteCollectionType);

            if (noteList==null) {
                noteList = new ArrayList<Note>();
            }
            noteList.add(noteToSave);
            notesContent = gson.toJson(noteList);

            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(noteFile));
            osw.write(notesContent);
            osw.flush();
            osw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
