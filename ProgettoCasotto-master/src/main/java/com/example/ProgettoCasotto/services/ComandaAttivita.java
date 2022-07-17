package com.example.ProgettoCasotto.services;

import com.example.ProgettoCasotto.models.Gioco;
import com.example.ProgettoCasotto.models.Sport;

import java.util.ArrayList;

public class ComandaAttivita {

    private ArrayList<Sport> ordini_sport;
    private ArrayList<Gioco> ordini_gioco;
    private String ID;

    public ComandaAttivita(ArrayList<Gioco> ordini_gioco, ArrayList<Sport> ordini_sport) {
        this.ordini_gioco = ordini_gioco;
        this.ordini_sport = ordini_sport;
    }

    public ComandaAttivita (){
        this.ordini_gioco = new ArrayList<>();
        this.ordini_sport = new ArrayList<>();
    }

    public void add_order_gioco(Gioco gioco){
        ordini_gioco.add(gioco);
    }

    public void add_order_sport(Sport sport){
        ordini_sport.add(sport);
    }

    public ArrayList<Gioco> getOrdini_gioco() {
        return ordini_gioco;
    }

    public void setOrdini_gioco(ArrayList<Gioco> ordini_gioco) {
        this.ordini_gioco = ordini_gioco;
    }

    public ArrayList<Sport> getOrdini_sport() {
        return ordini_sport;
    }

    public void setOrdini_sport(ArrayList<Sport> ordini_sport) {
        this.ordini_sport = ordini_sport;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
