package com.example.designmodel.Memento;

public class Client {
    public static void main(String[] args) {
        Editor editor = new Editor(new Doc("《AI觉醒》"));
        editor.append("一、前言 \n");
        editor.append("二、引言 \n");
        editor.append("三、正文 \n");
        editor.delete();
        editor.undo();
        editor.undo();
        editor.save();
    }


}
