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
    private final String noteLogFileName = "notes.json";

    public NotesHandler() {
        arrNotes = this.getNoteListOfTheFile();
    }

    /**
     * Busca la nota en el registro de notas del archivo que está guardado en memoría. Si encuentra
     * la nota ésta se elimina.
     * @param noteTitleToDelete titulo de la nota a eliminar .
     * @return retorna true si se encontró y se eliminó la nota. Si la nota no existe se retona false.
     */
    public boolean deleteNoteOfTheFile(String noteTitleToDelete) {
        if (this.arrNotes != null) {
            Note noteToDelete = this.getNoteOfTheList(noteTitleToDelete);
            if (noteToDelete!= null) {
                this.arrNotes.remove(noteToDelete);
                this.saveNoteListInTheFile(this.arrNotes);
                return true;
            }
        }

        return false;
    }

    /**
     * Del archivo de notas que se guarda en memoría se extrae el registro de todas las notas
     * guardadas, y retorna una lista con todas estas notas
     * @return Una lista con elementos de la clase Note. Si no hay notas en registradas, se
     * retorna null.
     */
    public ArrayList<Note> getNoteListOfTheFile() {
        File SDTarget = Environment.getExternalStorageDirectory();
        File noteFile = new File(SDTarget.getAbsolutePath(), noteLogFileName);

        String notesContent = "";

        //read the file
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

        if ( !notesContent.isEmpty() ) {
            Type noteCollectionType = new TypeToken<Collection<Note>>(){}.getType();
            Gson gson = new Gson();
            return gson.fromJson(notesContent, noteCollectionType);
        } else  {
            return null;
        }
    }

    /**
     * Recibe una lista de notas y la guarda en el archivo que está en memoría.
     * @param noteListToSave la lista con elementos de la clase Note ha guardar.
     */
    private void saveNoteListInTheFile(ArrayList<Note> noteListToSave) {
        Gson gson = new Gson();
        String noteListJSONString = gson.toJson(noteListToSave);

        File SDTarget = Environment.getExternalStorageDirectory();
        File noteFile = new File(SDTarget.getAbsolutePath(), noteLogFileName);

        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(noteFile));
            osw.write(noteListJSONString);
            osw.flush();
            osw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Guarda una nota en el registro de notas del archivo que está guardado en memoría.
     * Si el titulo de la nota ya existe, se modifica el cuerpo de esa nota.
     * @param noteToSaveInTheFile la nota que se va a guardar en el archivo.
     */
    public void saveNoteInTheFile(Note noteToSaveInTheFile) {
        if (this.arrNotes == null) {
            arrNotes = new ArrayList<Note>();
        }

        if (this.arrNotes.contains(noteToSaveInTheFile)) {
            int indexOfNoteToUpdated = this.arrNotes.indexOf(noteToSaveInTheFile);
            try {
                this.arrNotes.add(indexOfNoteToUpdated, noteToSaveInTheFile);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else {
            this.arrNotes.add(noteToSaveInTheFile);
        }
        this.saveNoteListInTheFile(this.arrNotes);
    }

    /**
     * De la lista de notas guardada en esta clase se obtiene la nota y se retorna.
     * @param titleOfNoteToGet el titulo de la nota a retornar
     * @return retorna una Note si se encuentra o null si no se encuentra.
     */
    public Note getNoteOfTheList(String titleOfNoteToGet) {
        for(Note noteOfTheList : this.arrNotes) {
            if (noteOfTheList.getTitle().equals(titleOfNoteToGet)) {
                return noteOfTheList;
            }
        }

        return null;
    }

    /**
     * De la lista de notas de esta clase, se obtiene todos los titulos y se retornan.
     * @return  una lista con todos los titulos de las notas.
     */
    public ArrayList<String> getTitlesOfAllTheNotes() {
        if(this.arrNotes != null) {
            ArrayList<String> notesTitlesOfTheList = new ArrayList<String>();
            for (Note noteOfTheList : this.arrNotes) {
                notesTitlesOfTheList.add(noteOfTheList.getTitle());
            }
            return notesTitlesOfTheList;
        }

        return null;
    }
}
