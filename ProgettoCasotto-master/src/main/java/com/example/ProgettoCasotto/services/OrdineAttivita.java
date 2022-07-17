package com.example.ProgettoCasotto.services;

public class OrdineAttivita {
    private String ID_attivita;
    private String ID_posto;
    private String partecipanti;
    private Double prezzo;


    public OrdineAttivita(String ID_attivita, String ID_posto, String partecipanti) {
        this.ID_attivita = ID_attivita;
        this.ID_posto = ID_posto;
        this.partecipanti = partecipanti;
        this.prezzo = 0.0;
    }
    public OrdineAttivita() {
        this.ID_attivita = null;
        this.ID_posto = null;
        this.partecipanti = null;
        this.prezzo = 0.0;

    }


    public String getID_attivita() {
        return ID_attivita;
    }

    public void setID_attivita(String ID_attivita) {
        this.ID_attivita = ID_attivita;
    }

    public String getID_posto() {
        return ID_posto;
    }

    public void setID_posto(String ID_posto) {
        this.ID_posto = ID_posto;
    }

    public String getPartecipanti() {
        return partecipanti;
    }

    public void setPartecipanti(String partecipanti) {
        this.partecipanti = partecipanti;
    }



    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }


}
