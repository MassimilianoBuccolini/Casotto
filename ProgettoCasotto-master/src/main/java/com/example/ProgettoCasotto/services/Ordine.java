package com.example.ProgettoCasotto.services;

public class Ordine {

    private String ID_posto;


    public Ordine(String ID_posto, String ID_bar) {
        this.ID_posto = ID_posto;
        this.ID_bar = ID_bar;
    }

    public Ordine() {
        this.ID_posto = ID_posto;
        this.ID_bar = ID_bar;
    }

    public String getID_posto() {
        return ID_posto;
    }

    public void setID_posto(String ID_posto) {
        this.ID_posto = ID_posto;
    }

    public String getID_bar() {
        return ID_bar;
    }

    public void setID_bar(String ID_bar) {
        this.ID_bar = ID_bar;
    }

    private String ID_bar;

}
