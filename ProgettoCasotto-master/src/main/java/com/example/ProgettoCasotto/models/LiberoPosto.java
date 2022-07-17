package com.example.ProgettoCasotto.models;


public class LiberoPosto {
    private  String qr;
    private  Integer capienza;



    /****************************************************/

/*
    public Posto(String persona, List<Attrezatura> attrezature,Orario tempo_prenotazione,Integer giorni_prenotazione){
        this.qr="aaaaa"; this.capienza=1; this.persona=persona;
        this.attrezature=attrezature;this.tempo_prenotazione=tempo_prenotazione;
        this.giorni_prenotazione=giorni_prenotazione;
    }
*/
    public LiberoPosto(String qr, Integer capienza){
        this.qr=qr; this.capienza=capienza;
    }

    public LiberoPosto(){}


    /****************************************************/

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public Integer getCapienza() {
        return capienza;
    }

    public void setCapienza(Integer capienza) {
        this.capienza = capienza;
    }


    @Override
    public String toString() {
        return "Posto{" +
                "qr='" + qr + '\'' +
                ", capienza=" + capienza +
                '}';
    }
}
