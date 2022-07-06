package com.example.ProgettoCasotto.models;

import java.util.ArrayList;

public class Piatto {

    private String nome;
    private double prezzo;
    private String ID;
    private String portata;
    private Integer disponibili;
    private ArrayList<String> Ingredienti;

    public Piatto(String nome, double prezzo, String ID, String portata , Integer disponibili) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.ID = ID;
        this.portata = portata;
        this.disponibili = disponibili;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public ArrayList<String> getIngredienti() {
        return Ingredienti;
    }

    public void setIngredienti(ArrayList<String> ingredienti) {
        Ingredienti = ingredienti;
    }

    public String getPortata() {
        return portata;
    }

    public void setPortata(String portata) {
        this.portata = portata;
    }

    public Integer getDisponibili() {
        return disponibili;
    }

    public void setDisponibili(Integer disponibili) {
        this.disponibili = disponibili;
    }

}
