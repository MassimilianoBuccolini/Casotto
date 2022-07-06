package com.example.ProgettoCasotto.util;

public class DettagliBevande {




    private String ID;
    private String nome;
    private Double prezzo;
    private Boolean alcolico;
    private Integer disponibilita;



    public DettagliBevande(String ID, String nome, Double prezzo, Boolean alcolico, Integer disponibilita ){
        this.ID=ID;
        this.nome=nome;
        this.prezzo=prezzo;
        this.alcolico=alcolico;
        this.disponibilita=disponibilita;
    }

    public DettagliBevande(String ID, Integer disponibilita
    ){
        this.ID=ID;
        this.disponibilita=disponibilita;
        this.nome=null;
        this.prezzo=null;
        this.alcolico=null;

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Integer getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(Integer disponibilita) {
        this.disponibilita = disponibilita;
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

    public Boolean getAlcolico() {
        return alcolico;
    }

    public void setAlcolico(Boolean alcolico) {
        this.alcolico = alcolico;
    }

    @Override
    public String toString() {
        return "DettagliCiboBevande{" +
                "ID='" + ID + '\'' +
                ", disponibilità =" + disponibilita +
                ", nome='" + nome + '\'' +
                '}';
    }

}

