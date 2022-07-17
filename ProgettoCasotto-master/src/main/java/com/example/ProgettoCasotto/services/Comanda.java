package com.example.ProgettoCasotto.services;

import com.example.ProgettoCasotto.models.Bibita;
import com.example.ProgettoCasotto.models.Piatto;

import java.util.ArrayList;

public class Comanda {

    private ArrayList<Piatto> ordini_piatti = new ArrayList<>();
    private ArrayList<Bibita> ordini_bibite = new ArrayList<>();
    private String ID;

    public Comanda(ArrayList<Piatto> ordini_piatti, ArrayList<Bibita> ordini_bibite) {
        this.ordini_piatti = ordini_piatti;
        this.ordini_bibite = ordini_bibite;
    }

    public Comanda (){
        this.ordini_piatti = new ArrayList<>();
        this.ordini_bibite = new ArrayList<>();
    }

    public void add_order_piatti(Piatto piatto){
        ordini_piatti.add(piatto);
    }

    public void add_order_bibite(Bibita bibita){
        ordini_bibite.add(bibita);
    }

    public ArrayList<Piatto> getOrdini_piatti() {
        return ordini_piatti;
    }

    public void setOrdini_piatti(ArrayList<Piatto> ordini_piatti) {
        this.ordini_piatti = ordini_piatti;
    }

    public ArrayList<Bibita> getOrdini_bibite() {
        return ordini_bibite;
    }

    public void setOrdini_bibite(ArrayList<Bibita> ordini_bibite) {
        this.ordini_bibite = ordini_bibite;
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
