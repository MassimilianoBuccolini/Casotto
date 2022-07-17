package com.example.ProgettoCasotto.models;

public class Sport {
    private String ID;
    private String nome;
    private Double prezzo;
    private Integer maxPartecipanti;
    private Integer numPrenotati;
    private Integer prenotatiSingoloPosto;

 public Sport(String ID, String nome, Double prezzo, Integer max) {
    this.ID = ID;
    this.nome = nome;
    this.prezzo = prezzo;
     this.numPrenotati = 0;
     this.maxPartecipanti= max;
     this.prenotatiSingoloPosto = 0;
}


    public Sport() {
     this.ID = null;
     this.nome = null;
     this.prezzo = 0.0;
        this.numPrenotati = 0;
        this.maxPartecipanti= 10;
        this.prenotatiSingoloPosto = 0;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }


    public Integer getMaxPartecipanti() {
        return maxPartecipanti;
    }

    public void setMaxPartecipanti(Integer maxPartecipanti) {
        this.maxPartecipanti = maxPartecipanti;
    }



    public Integer getNumPrenotati() {
        return numPrenotati;
    }

    public void setNumPrenotati(Integer numPrenotati) {
        this.numPrenotati = numPrenotati;
    }


    public Integer getPrenotatiSingoloPosto() {
        return prenotatiSingoloPosto;
    }

    public void setPrenotatiSingoloPosto(Integer prenotatiSingoloPosto) {
        this.prenotatiSingoloPosto = prenotatiSingoloPosto;
    }

}