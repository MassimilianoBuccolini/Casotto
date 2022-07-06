package com.example.ProgettoCasotto.util;

public class DettagliCibo
{
    public String ID;
    public String nome;
    public Double prezzo;
    public String portata;
    public Integer disponibilita;


    public DettagliCibo(String ID, String nome, Double prezzo, String portata, Integer disponibilita) {
        this.ID=ID;
        this.nome = nome;
        this.prezzo = prezzo;
        this.portata = portata;
        this.disponibilita = disponibilita;
    }

    public DettagliCibo(String ID, Integer disponibilita){
        this.ID=ID;
        this.disponibilita =disponibilita;
        this.nome=null;
        this.prezzo=null;
        this.portata=null;

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
