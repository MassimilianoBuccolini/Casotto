package com.example.ProgettoCasotto.models;

public class Bibita {

    private String ID;
    private String Nome;
    private double Prezzo;
    private boolean alcolico;
    private Integer disponibilità;

    public Bibita(String ID, String nome,  boolean alcolico, Integer disponibilità, Double prezzo) {
        this.ID = ID;
        Nome = nome;
        Prezzo = prezzo;
        this.alcolico = alcolico;
        this.disponibilità = disponibilità;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public double getPrezzo() {
        return Prezzo;
    }

    public void setPrezzo(double prezzo) {
        Prezzo = prezzo;
    }

    public boolean isAlcolico() {
        return alcolico;
    }

    public void setAlcolico(boolean alcolico) {
        this.alcolico = alcolico;
    }

    public Integer getDisponibilità() {
        return disponibilità;
    }

    public void setDisponibilità(Integer disponibilità) {
        this.disponibilità = disponibilità;
    }
}