package com.leoky.queuee.api.model;

public class UserList {
    private String title,body,note;
    private int show;

    public UserList(String title, String body, String note, int show) {
        this.title = title;
        this.body = body;
        this.note = note;
        this.show = show;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getShow() {
        return show;
    }

    public void setShow(int show) {
        this.show = show;
    }
}

