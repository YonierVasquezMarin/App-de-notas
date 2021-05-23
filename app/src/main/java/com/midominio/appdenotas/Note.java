package com.midominio.appdenotas;

/**
 * Una nota con título y cuerpo del mensaje.
 * @author Yonier Vasquez Marin
 * @version 1.0
 */
public class Note {
    private String title, messageBody;

    public Note(String title, String messageBody) {
        this.title = title;
        this.messageBody = messageBody;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", messageBody='" + messageBody + '\'' +
                '}';
    }
}
