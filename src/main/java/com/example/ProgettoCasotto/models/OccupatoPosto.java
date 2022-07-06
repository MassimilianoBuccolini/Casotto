package com.example.ProgettoCasotto.models;

import com.example.ProgettoCasotto.services.Attrezatura;
import com.example.ProgettoCasotto.services.Orario;

import java.util.ArrayList;
import java.util.List;

public class OccupatoPosto extends LiberoPosto {

    private String persona;
    private List<Attrezatura> attrezature=new ArrayList<>();
    private Orario tempo_prenotazione;
    private Integer giorni_prenotazione;

    // comanda bar , comanda attivita manca
    // manca libera posto


    public OccupatoPosto(String qr, Integer capienza,
                         String persona, List<Attrezatura> attrezature, Orario tempo_prenotazione, Integer giorni_prenotazione){
        super(qr,capienza);
        this.persona=persona;
        this.attrezature=attrezature;
        this.tempo_prenotazione=tempo_prenotazione;
        if (tempo_prenotazione.equals(Orario.GIORNI_CONSECUTIVI)){
            this.giorni_prenotazione=giorni_prenotazione;}
    }

    //quando inizi form / manca qr e capienza se lo rimuovi;
    public OccupatoPosto(LiberoPosto l){
        super(l.getQr(), l.getCapienza());
    }

    // viene errore se lo rimuovi;
    public OccupatoPosto(){}



    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public List<Attrezatura> getAttrezature() {
        return attrezature;
    }

    public void setAttrezature(List<Attrezatura> attrezature) {
        this.attrezature = attrezature;
    }

    public Orario getTempo_prenotazione() {
        return tempo_prenotazione;
    }

    public void setTempo_prenotazione(Orario tempo_prenotazione) {
        this.tempo_prenotazione = tempo_prenotazione;
    }

    public Integer getGiorni_prenotazione() {
        return giorni_prenotazione;
    }

    public void setGiorni_prenotazione(Integer giorni_prenotazione) {
        this.giorni_prenotazione = giorni_prenotazione;
    }


    @Override
    public String toString() {
        return "OccupatoPosto{" +
                "persona='" + persona + '\'' +
                ", attrezature=" + attrezature +
                ", tempo_prenotazione=" + tempo_prenotazione +
                ", giorni_prenotazione=" + giorni_prenotazione +
                '}';
    }
}
