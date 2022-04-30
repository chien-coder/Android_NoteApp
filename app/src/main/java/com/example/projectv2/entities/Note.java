package com.example.projectv2.entities;

public class Note {

    private int id;

    private String title;

    private String dateTime;

    private String subtitle;

    private String noteText;

    private String imgPath;

    private String color;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Note(String title, String dateTime, String subtitle, String noteText, String imgPath, String color) {
        this.title = title;
        this.dateTime = dateTime;
        this.subtitle = subtitle;
        this.noteText = noteText;
        this.imgPath = imgPath;
        this.color = color;
    }

    public Note(int id,String title, String dateTime, String subtitle, String noteText, String imgPath, String color) {
        this.id = id;
        this.title = title;
        this.dateTime = dateTime;
        this.subtitle = subtitle;
        this.noteText = noteText;
        this.imgPath = imgPath;
        this.color = color;
    }
}
